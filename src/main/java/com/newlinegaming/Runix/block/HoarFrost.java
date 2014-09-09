package com.newlinegaming.Runix.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.newlinegaming.Runix.RunixMain;
import com.newlinegaming.Runix.SigBlock;
import com.newlinegaming.Runix.Vector3;
import com.newlinegaming.Runix.WorldXYZ;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HoarFrost extends BlockIce {
    
    public HoarFrost() {
        super();
        setTickRandomly(true);
        setCreativeTab(RunixMain.TabRunix);
        setHardness(0.5F);
        setStepSound(soundTypeGlass);
        setBlockName("runix:hoarfrost");
        setBlockTextureName("ice_packed");
    }
    
    @Override
    public int damageDropped (int metadata) {
        return metadata;
    }
    
    @SuppressWarnings("rawtypes") 
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
        int[] growthModes = {0, 1, 2, 3, 4, 5, 7, 14};
        for (int ix = 0; ix < growthModes.length; ix++) {
            subItems.add(new ItemStack(this, 1, growthModes[ix]));
        }
    }
    
    @Override
    public int tickRate(World par1World) {
        return 10 + par1World.rand.nextInt(10);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
//        new WorldXYZ(world, x, y, z).setBlock(ModBlock.frostOrigin, 15);//this line is to convert all existing frost blocks
        int growthMode = world.getBlockMetadata(x, y, z);

        if(growthMode == 0) {//Origin Sequence
            world.scheduleBlockUpdate(x, y, z, this, 200);
            ArrayList<WorldXYZ> neighbors = new WorldXYZ(world, x, y, z).getDirectNeighbors();
            for(WorldXYZ n : neighbors){
                n.setBlock(ModBlock.hoar_frost, 1);//create crawl expansion blocks
            }
            new WorldXYZ(world, x, y, z).setBlock(ModBlock.hoar_frost, 3);// next phase in the sequence
        }

        if(growthMode == 1) {//surface crawl
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world)); //schedule for child
            int randomIndex = random.nextInt(Vector3.conductanceNeighbors.length);
            WorldXYZ randomNeighbor = new WorldXYZ(world, x, y, z).offset(Vector3.conductanceNeighbors[randomIndex]);
            if(randomNeighbor.getBlock().equals(Blocks.air)) {
                ArrayList<WorldXYZ> indirectNeighbors = randomNeighbor.getDirectNeighbors();
                for(WorldXYZ base : indirectNeighbors) {
                    Block block = base.getBlock();
                    if( !block.equals(ModBlock.hoar_frost) && !block.equals(Blocks.air) ) { //found a valid growth location
                        randomNeighbor.setBlock(ModBlock.hoar_frost, growthMode);
                        world.scheduleBlockUpdate(randomNeighbor.posX, randomNeighbor.posY, randomNeighbor.posZ, this, this.tickRate(world)); //schedule for child
                        return;
                    }
                }
            }
        }
        
        if(growthMode == 3) { //infectious shutdown stasis mode
            ArrayList<WorldXYZ> neighbors = new WorldXYZ(world, x, y, z).getNeighbors();
            for(WorldXYZ n : neighbors){
                SigBlock data = n.getSigBlock();
                if(data.blockID.equals(ModBlock.hoar_frost) && data.meta != growthMode) {
                    n.setBlock(ModBlock.hoar_frost, growthMode);
                    world.scheduleBlockUpdate(n.posX, n.posY, n.posZ, this, 5); //schedule for neighbor
                }
            }
        }

        if(growthMode == 7) { //Expanding shell
            ArrayList<WorldXYZ> neighbors = new WorldXYZ(world, x, y, z).getDirectNeighbors();
            int nCount = 0;
            for(WorldXYZ n : neighbors){
                if(n.getBlock().equals(Blocks.air))
                    ++nCount;
            }
            if(nCount == 0){
                new WorldXYZ(world, x, y, z).setBlock(ModBlock.runixAir, 0);
            } else {
                WorldXYZ target = neighbors.get(random.nextInt(neighbors.size()));
                if( target.getBlock().equals(Blocks.air))
                    target.setBlock(ModBlock.hoar_frost, growthMode);
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            }
        }
        
        if(growthMode == 14) { //infectious delete mode
            WorldXYZ me = new WorldXYZ(world, x, y, z);
            ArrayList<WorldXYZ> neighbors = me.getNeighbors();
            for(WorldXYZ n : neighbors){
                if(n.getBlock().equals(ModBlock.hoar_frost)){
                    n.setBlock(ModBlock.hoar_frost, growthMode); //spread the deletion
                    world.scheduleBlockUpdate(n.posX, n.posY, n.posZ, this, 3); //update neighbor quickly
                }
            }
            me.setBlock(Blocks.air, 0); //delete self
        }
        
    }
    
    @Override
    public void onBlockAdded(World par1World, int x, int y, int z)
    {
        par1World.scheduleBlockUpdate(x, y, z, this, this.tickRate(par1World));
    }
}
