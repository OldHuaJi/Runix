package com.newlinegaming.runix;

import com.newlinegaming.runix.rune.AbstractRune;
import com.newlinegaming.runix.rune.PersistentRune;
import com.newlinegaming.runix.rune.RunixRune;

import com.newlinegaming.runix.rune.handlers.RuneHandler;
import com.newlinegaming.runix.core.lib.LibInfo;

import com.newlinegaming.runix.world.RunixWorld;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import us.illyohs.azathoth.pulsar.config.ForgeCFG;
import us.illyohs.azathoth.pulsar.control.PulseManager;

@Mod(modid = LibInfo.MOD_ID, name = LibInfo.MOD_NAME, version = LibInfo.MOD_VERSION, dependencies = "required-after:Azathoth")
public class RunixMain {


    @Instance
    public static RunixMain instance;

    public static PulseManager puls = new PulseManager(LibInfo.MOD_ID, new ForgeCFG("RunixPulses",
            "Don't disable these unless you want some weird randomness to happen"));

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        puls.registerPulse(new RunixRune());
        puls.registerPulse(new RunixWorld());

        puls.preInit(event);

    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {

        puls.init(event);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        puls.postInit(event);

    }
    
    @EventHandler
    public void serverStop(FMLServerStoppingEvent event) {
	System.out.println("Clearing all runes");
        for(AbstractRune r : RuneHandler.getInstance().runeRegistry)
            if( r instanceof PersistentRune)
                ((PersistentRune) r).clearActiveMagic();
    }
}
