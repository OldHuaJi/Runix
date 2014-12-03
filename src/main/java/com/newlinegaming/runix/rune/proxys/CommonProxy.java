package com.newlinegaming.runix.rune.proxys;

import com.newlinegaming.runix.rune.tile.TileLightBeam;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerRenderInformation() {} //NO-OP
    
    public void registerTileEnitiy() {
        GameRegistry.registerTileEntity(TileLightBeam.class, "TileLightBeam");
    }
}
