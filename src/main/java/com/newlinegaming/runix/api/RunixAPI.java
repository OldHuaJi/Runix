package com.newlinegaming.runix.api;


import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class RunixAPI {

    //Tool and armor Materials
    public static ItemArmor.ArmorMaterial armorRunix = EnumHelper.addArmorMaterial("RUNEIUMARMOR", 30, new int[]{4, 6, 6, 4}, 25);
    public static ItemArmor.ArmorMaterial armorArcadian = EnumHelper.addArmorMaterial("ARCADIANARMOR", 50, new int[]{4, 6, 6, 4}, 25);
    public static Item.ToolMaterial toolRunix = EnumHelper.addToolMaterial("RUNEIUMTOOL", 4, 650, 5, 4, 25);
    public static Item.ToolMaterial toolArcadian = EnumHelper.addToolMaterial("ARCADIANARMOR", 4, 800, 5, 6, 25);

}
