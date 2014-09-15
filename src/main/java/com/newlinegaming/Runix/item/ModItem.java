package com.newlinegaming.Runix.item;

import com.newlinegaming.Runix.item.armor.ArmorAetherGoggles;
import com.newlinegaming.Runix.item.rods.BaseRod;
import com.newlinegaming.Runix.item.rods.ItemTransmutationRod;
import com.newlinegaming.Runix.lib.LibInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModItem {

    public static Item aetherGoggles;
    public static BaseRod transRod;

    public static void init() {

        aetherGoggles = new ArmorAetherGoggles();
        transRod = new ItemTransmutationRod();
        
        gameReg();
        gameRecipes();
    }

    private static void gameReg() {
        GameRegistry.registerItem(aetherGoggles, LibInfo.MOD_ID  + "aethergoggles");
        GameRegistry.registerItem(transRod, LibInfo.MOD_ID + "transmutationrod");
        
    }
    
    private static void gameRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(transRod), "x", "x", "x", 'x', "stickWood"));//Temporary
    }
}
