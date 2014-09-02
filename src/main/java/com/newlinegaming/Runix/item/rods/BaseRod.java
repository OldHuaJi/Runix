package com.newlinegaming.Runix.item.rods;

import java.util.List;

import com.newlinegaming.Runix.RunixMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BaseRod extends Item implements IRod {
    
    public BaseRod() {
        super();
        setCreativeTab(RunixMain.TabRunix);
        setMaxStackSize(1);
    }
    
    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player) {
        is.stackTagCompound = new NBTTagCompound();
        is.stackTagCompound.setInteger("MinEnergy", getMinEnergy());
        is.stackTagCompound.setInteger("MaxEnergy", getMaxEnrgy());
        is.stackTagCompound.setInteger("CurrentEnergy", getCurrentEnergy());
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        if (is.stackTagCompound != null) {
            //HitBlock
                //if mode == eat consume block | Will be done with oracle
                //if mode == Transmute transmute block
                //if mode == Rinic activator activate rune
        }
        return is;
    }

    @Override
    public int getCurrentEnergy() {
        return 0;
    }

    @Override
    public int getMaxEnrgy() {
        return 0;
    }

    @Override
    public int getMinEnergy() {
        return 0;
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
