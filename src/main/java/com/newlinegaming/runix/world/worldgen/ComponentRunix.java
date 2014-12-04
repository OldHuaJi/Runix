package com.newlinegaming.runix.world.worldgen;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Random;


public class ComponentRunix extends StructureComponent {

    protected ComponentRunix(int direction) {
        super(direction);
        coordBaseMode = direction;
    }


    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        return true;
    }

    /**
     * Build component
     */
    public boolean addComponentParts(World world, Random random) {
        return true;
    }

    /**
     * Place block
     */
    @Override
    public void placeBlockAtCurrentPosition(World world, Block block, int blockMeta, int x, int y, int z, StructureBoundingBox boundingBox) {
        super.placeBlockAtCurrentPosition(world, block, blockMeta, x, y, z, boundingBox);
    }

    /**
     * Return world X coord
     *
     * @param x Bounding box X coord
     * @param z Bounding box Z coord
     */
    @Override
    public int getXWithOffset(int x, int z) {
        return super.getXWithOffset(x, z);
    }

    /**
     * Return world y coord
     *
     * @param y Bounding box Y coord
     */
    @Override
    public int getYWithOffset(int y) {
        return super.getYWithOffset(y);
    }

    /**
     * Return world Z coord
     *
     * @param x Bounding box X coord
     * @param z Bounding box Z coord
     */
    @Override
    public int getZWithOffset(int x, int z) {
        return super.getZWithOffset(x, z);
    }

    /**
     * Used to generate chests with items in it. ex: Temple Chests, Village
     * Blacksmith Chests, Mineshaft Chests.
     */
    @Override
    public boolean generateStructureChestContents(World world, StructureBoundingBox boundingBox, Random random, int x, int y, int z, WeightedRandomChestContent[] chestContent, int par8) {
        return super.generateStructureChestContents(world, boundingBox, random, x, y, z, chestContent, par8);
    }

    /**
     * Write structure to NBT
     */
    @Override
    protected void func_143012_a(NBTTagCompound nbttagcompound) {
    }

    /**
     * Read structure from nbt
     */
    @Override
    protected void func_143011_b(NBTTagCompound nbttagcompound) {
    }
}
