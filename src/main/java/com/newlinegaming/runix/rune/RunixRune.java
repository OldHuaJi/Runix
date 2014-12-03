package com.newlinegaming.runix.rune;

import com.newlinegaming.runix.api.energy.Tiers;
import com.newlinegaming.runix.core.lib.LibInfo;
import com.newlinegaming.runix.rune.block.ModBlock;
import com.newlinegaming.runix.rune.creativetabs.TabRunix;
import com.newlinegaming.runix.rune.fluids.ModFluid;
import com.newlinegaming.runix.rune.handlers.RuneHandler;
import com.newlinegaming.runix.rune.item.ModItem;
import com.newlinegaming.runix.rune.proxys.CommonProxy;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import us.illyohs.azathoth.pulsar.pulse.Handler;
import us.illyohs.azathoth.pulsar.pulse.Pulse;

@ObjectHolder(LibInfo.MOD_ID)
@Pulse(id = "Runix|runes", description = "The Rune module of Runix")
public class RunixRune {

    @Instance("Runix|runes")
    public static RunixRune instance;

    @SidedProxy(clientSide = "com.newlinegaming.runix.rune.proxys.ClientProxy",
                serverSide = "com.newlinegaming.runix.rune.proxys.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs TabRunix = new TabRunix(LibInfo.MOD_ID + ":runix");

    @Handler
    public void preInit(FMLPreInitializationEvent event) {

        ModBlock.init();
        ModFluid.init();
        ModItem.init();
    }

    @Handler
    public void Init(FMLInitializationEvent event) {
        proxy.registerRenderInformation();
        proxy.registerTileEnitiy();

    }

    @Handler
    public void postInit(FMLPostInitializationEvent event) {

        Tiers tiers = new Tiers(); //load the list of block tiers
        tiers.initializeEnergyRegistry();
        MinecraftForge.EVENT_BUS.register(RuneHandler.getInstance());

    }

}
