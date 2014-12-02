package com.newlinegaming.runix;

import static org.junit.Assert.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import org.junit.Test;

public class Tests {

    @Test
    public void BlockNameEqualityTest() {
        Block a = Blocks.redstone_wire;
        SigBlock b = new SigBlock(a, 0);
//        assertTrue( a == b );
//        assertTrue( b.equals(a) );
        Block tier = AbstractRune.TIER;
        System.out.println(tier.getUnlocalizedName());
        assertTrue(tier.getUnlocalizedName().equals("tile.TIER"));
    }

    @Test
    public void SigBlockEqualityTest() {
        assertTrue(new SigBlock(AbstractRune.TIER, 0).equals(AbstractRune.TIER) );
        //This does not work:
        //assertTrue(AbstractRune.TIER == new SigBlock(AbstractRune.TIER, 0));
    }
}
