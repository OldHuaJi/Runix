package com.newlinegaming.Runix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**PhantomTorch functionality to place permanent torches appropriately spaced to prevent monster spawn.*/
public class PhantomTorchRune extends AbstractTimedRune {
    protected static ArrayList<PersistentRune> activeMagic = new ArrayList<PersistentRune>();
    private boolean disabled = false;
    
    public PhantomTorchRune() {}

    public PhantomTorchRune(EntityPlayer activator, WorldXYZ coords) {
        super(coords, activator);
        consumeRune(coords);
        updateEveryXTicks(10);
    }

    @Override
    protected void onUpdateTick(EntityPlayer subject) {
        if(!disabled && subject.equals(player) && !subject.worldObj.isRemote)
        {
            World world = subject.worldObj;//sphere can be optimized to donut
            //location is not a good criteria for activeMagic collisions, this is solved by constantly updating location
            location = new WorldXYZ(player);
            LinkedList<WorldXYZ> sphere = Util_SphericalFunctions.getShell(location, 7);
            for(WorldXYZ newPos : sphere)
            {
                Material base = world.getBlockMaterial( ((int)newPos.posX), ((int)newPos.posY-1), ((int)newPos.posZ) );
                if(newPos.getBlockId() == 0 && base.isSolid() && 
                        world.getBlockLightValue(newPos.posX, newPos.posY, newPos.posZ) < 4){ //adjustable
                    newPos.setBlockId(Block.torchWood.blockID);//set torch
                    energy -= Tiers.getEnergy(Block.torchWood.blockID);
                    if(energy < Tiers.getEnergy(Block.torchWood.blockID))
                        disabled = true; //TODO: kill thyself
                    return; //Light levels don't update til the end of the tick, so we need to exit
                }
            }
        }
    }

    @Override
    public int[][][] blockPattern() {
        int REDW = Block.redstoneWire.blockID;
        int TRCH = Block.torchWood.blockID;
        return new int [][][] 
                {{{REDW,TRCH,REDW},
                  {TRCH,TIER,TRCH},
                  {REDW,TRCH,REDW}}}; 
    }

    @Override
    public void execute(EntityPlayer player, WorldXYZ coords) {
        if(!player.worldObj.isRemote)
            activeMagic.add(new PhantomTorchRune(player, coords));
    }

    @Override
    public String getRuneName() {
        return "Phantom Torch";
    }

    @Override
    public ArrayList<PersistentRune> getActiveMagic() {
        return activeMagic;
    }

}