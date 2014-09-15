package com.newlinegaming.Runix.item.rods;

import com.newlinegaming.Runix.RunixMain;
import com.newlinegaming.Runix.api.energy.IItemEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class BaseRod extends Item implements IItemEnergy {
    
    public BaseRod() {
        super();
        setCreativeTab(RunixMain.TabRunix);
        setMaxStackSize(1);
    }
    
    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player) {
        is.stackTagCompound = new NBTTagCompound();
        
        //Energy
//        is.stackTagCompound.setInteger("MinEnergy", getMinEnergy(minEnergy));
        
//        is.stackTagCompound.setInteger("MaxEnergy", getMaxEnergy(maxEnergy));
        
        is.stackTagCompound.setInteger("CurrentEnergy", 0);
        //Mode
//        is.stackTagCompound.setString("Mode", getMode());
    }
    
//    public static v
    
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        return is;    
    }
    
    public String getMode() {
        //TODO make modes.
        return null;
    }

//    public int getCharge(int charge) {
//        //TODO set charge sizes for the
//        return charge;
//    }
    @Override
    public int setCurrentEnergy(ItemStack is, int energy){
        int oldEnergy = getCurrentEnergy(is);
        is.stackTagCompound = new NBTTagCompound();
        is.stackTagCompound.setInteger("CurrentEnergy", energy + oldEnergy);
        return getCurrentEnergy(is);
    }

    @Override
    public int getCurrentEnergy(ItemStack is) {
        return is.stackTagCompound.getInteger("CurrentEnergy");
    }

    @Override
    public int getMaxEnergy(ItemStack is,int max) {
        return max;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4) {
        if (is.stackTagCompound !=null) {
            int max = is.stackTagCompound.getInteger("MaxEnergy");
            int current = is.stackTagCompound.getInteger("CurrentEnergy");

            list.add(StatCollector.translateToLocal(EnumChatFormatting.DARK_PURPLE + "This has: " + EnumChatFormatting.DARK_AQUA + current + " Energy"));
            list.add(StatCollector.translateToLocal(EnumChatFormatting.DARK_PURPLE + "This rod can hold: " + EnumChatFormatting.DARK_AQUA + max + " Energy" ));
        }
      
  }

  
}
