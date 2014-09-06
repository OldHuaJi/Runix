package com.newlinegaming.Runix.item.rods;

import java.util.List;

import com.newlinegaming.Runix.RunixMain;
import com.newlinegaming.Runix.WorldXYZ;
import com.newlinegaming.Runix.api.energy.IItemEnergy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BaseRod extends Item implements IItemEnergy {
    
    public int current;
    public int max;
    public int min;
    
    public BaseRod() {
        super();
        setCreativeTab(RunixMain.TabRunix);
        setMaxStackSize(1);
        getCurrentEnergy(current);
    }
    
    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player) {
        is.stackTagCompound = new NBTTagCompound();
        
        //Energy
        is.stackTagCompound.setInteger("MinEnergy", getMinEnergy(min));
        
        is.stackTagCompound.setInteger("MaxEnergy", getMaxEnrgy(max));
        
        is.stackTagCompound.setInteger("CurrentEnergy", getCurrentEnergy(current));
//        is.stackTagCompound.setInteger("Charge", getCharge(int charge));
        //Mode
        is.stackTagCompound.setString("Mode", getMode());
    }
    
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        return is;    
    }
    
    public String getMode() {
        return null;
    }

    public int getCharge(int charge) {
        return charge;
    }
   
    @Override
    public int getCurrentEnergy(int current) {
        return current;
    }

    @Override
    public int getMaxEnrgy(int max) {
        return max;
    }

    @Override
    public int getMinEnergy(int min) {
        return min;
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
