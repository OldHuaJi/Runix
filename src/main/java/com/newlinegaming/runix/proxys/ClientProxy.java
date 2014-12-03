package com.newlinegaming.runix.proxys;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.newlinegaming.runix.client.render.item.ItemRenderTransmutaionRod;
import com.newlinegaming.runix.client.render.tile.RenderTileLightBeam;
import com.newlinegaming.runix.client.renderer.block.GreekFireRenderer;
import com.newlinegaming.runix.item.ModItem;
import com.newlinegaming.runix.tile.TileLightBeam;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
    @Override
	public void registerRenderInformation() {
//	    ISimpleBlockRenderingHandler handler = null;
//	    RenderingRegistry.registerBlockHandler(handler);
	    ClientRegistry.bindTileEntitySpecialRenderer(TileLightBeam.class, new RenderTileLightBeam());
	    MinecraftForgeClient.registerItemRenderer(ModItem.transRod, (IItemRenderer)new ItemRenderTransmutaionRod());
	    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler) new GreekFireRenderer());
	}
	
	public void registerTileEnitiy() {} //NO OP
}