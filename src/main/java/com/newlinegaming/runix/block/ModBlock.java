package com.newlinegaming.runix.block;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ModBlock {

    public static GreekFire greekFire;
    
    //Fake/replacement Blocks
    public static Block lightBeam;
    public static Block fakeGoldBlock;
    
    public static void init() {

        greekFire = GreekFire.getInstance();
    	

    	lightBeam = new BlockLightBeam();

    	fakeGoldBlock = new FakeBlock(Blocks.gold_block);


        Gamereg();
        

    }

    private static void Gamereg() {

        GameRegistry.registerBlock(greekFire, "GreekFire");
        GameRegistry.registerBlock(lightBeam, "RunixLightBeam");
        GameRegistry.registerBlock(fakeGoldBlock, "RunixFakeGoldBlock");
    }
}
