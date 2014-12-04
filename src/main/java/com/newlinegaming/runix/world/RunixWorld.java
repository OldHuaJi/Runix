package com.newlinegaming.runix.world;


import cpw.mods.fml.common.Mod.Instance;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import us.illyohs.azathoth.pulsar.pulse.Handler;
import us.illyohs.azathoth.pulsar.pulse.Pulse;

@Pulse(id = "Runix|World", description = "The worldgen module of Runix")
public class RunixWorld {


    @Instance("Runix|World")
    public static RunixWorld instance;

    @Handler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Handler
    public void Init(FMLInitializationEvent event) {

    }

    @Handler
    public void PostInit(FMLPostInitializationEvent event) {

    }
}
