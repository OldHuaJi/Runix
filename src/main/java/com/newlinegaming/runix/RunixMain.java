package com.newlinegaming.runix;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import com.newlinegaming.runix.block.ModBlock;
import com.newlinegaming.runix.creativetabs.TabRunix;
import com.newlinegaming.runix.fluids.ModFluid;
import com.newlinegaming.runix.handlers.RuneHandler;
import com.newlinegaming.runix.item.ModItem;
import com.newlinegaming.runix.lib.LibInfo;
import com.newlinegaming.runix.proxys.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = LibInfo.MOD_ID, name = LibInfo.MOD_NAME, version = LibInfo.MOD_VERSION)
public class RunixMain {

    //Tool and armor Materials
    public static ArmorMaterial armorRunix = EnumHelper.addArmorMaterial("RUNEIUMARMOR", 30, new int[] { 4, 6, 6, 4 }, 25);
    public static ArmorMaterial armorArcadian = EnumHelper.addArmorMaterial("ARCADIANARMOR", 50, new int[]{4, 6, 6, 4}, 25);
    public static ToolMaterial toolRunix = EnumHelper.addToolMaterial("RUNEIUMTOOL", 4, 650, 5, 4, 25);
    public static ToolMaterial toolArcadian = EnumHelper.addToolMaterial("ARCADIANARMOR", 4, 800, 5, 6, 25);

    @Instance
    public static RunixMain instance;

    @SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs TabRunix = new TabRunix(LibInfo.MOD_ID + ":runix");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + LibInfo.MOD_NAME + ".cfg"));
        
        
        ModBlock.init();
        
        ModFluid.init();
        
        ModItem.init();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
        
        proxy.registerRenderInformation();
        proxy.registerTileEnitiy();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        Tiers tiers = new Tiers(); //load the list of block tiers
        tiers.initializeEnergyRegistry();
        MinecraftForge.EVENT_BUS.register(RuneHandler.getInstance());
    }
    
    @EventHandler
    public void serverStop(FMLServerStoppingEvent event){
	System.out.println("Clearing all runes");
        for(AbstractRune r : RuneHandler.getInstance().runeRegistry)
            if( r instanceof PersistentRune)
                ((PersistentRune) r).clearActiveMagic();
    }
}
