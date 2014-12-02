package com.newlinegaming.runix.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RunixAirBlock extends Block {

    public RunixAirBlock() {
        super(Material.air);
    }

    public int getRenderType() {
        return -1;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_){
        return false;
    }

 
    public void dropBlockAsItemWithChance(World world, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}


}
