package com.newlinegaming.runix.rune;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class RuneTimer {

    AbstractTimedRune rune;
    private int currentTimer = 0;
    private int maxTimer = 20;

    RuneTimer(AbstractTimedRune r, int waitTicks){
        rune = r;
        currentTimer = 0;
        maxTimer = waitTicks;
    }
    
    @SubscribeEvent
    public void onPlayerTickEvent(PlayerTickEvent event) {
    	++currentTimer;
    	if(currentTimer >= maxTimer) {
    		currentTimer = 0;
    		if(!rune.disabled) {
    		    rune.onUpdateTick(event.player);
    		}

    	}
    }
    
    public String getLabel() {
        return null;
    }
    
}