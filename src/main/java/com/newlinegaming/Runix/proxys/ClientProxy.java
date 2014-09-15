package com.newlinegaming.Runix.proxys;

import com.newlinegaming.Runix.client.render.tile.RenderTileLightBeam;
import com.newlinegaming.Runix.tile.TileLightBeam;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
    @Override
	public void registerRenderInformation() {
//	    ISimpleBlockRenderingHandler handler = null;
//	    RenderingRegistry.registerBlockHandler(handler);
	    ClientRegistry.bindTileEntitySpecialRenderer(TileLightBeam.class, new RenderTileLightBeam());
//	    MinecraftForgeClient.registerItemRenderer(ModItem.transRod, (IItemRenderer)new ItemRenderTransmutaionRod());
	}
	
	public void registerTileEnitiy() {} //NO OP
}
