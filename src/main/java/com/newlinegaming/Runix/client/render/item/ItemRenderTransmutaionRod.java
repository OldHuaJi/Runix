package com.newlinegaming.Runix.client.render.item;

import org.lwjgl.opengl.GL11;

import com.newlinegaming.Runix.client.models.ModelTransmutationRod;
import com.newlinegaming.Runix.lib.LibInfo;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderTransmutaionRod implements IItemRenderer {
    
    private ModelTransmutationRod model;
    
    public ItemRenderTransmutaionRod() {
        model = new ModelTransmutationRod();
    }
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibInfo.MOD_ID, "textures/models/transrod.png");

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data) {
        switch (itemRenderType) {
            case ENTITY: {
                renderRod(0.5f, 0.5f, 0.5f, itemStack.getItemDamage());
                break;
            }
            
            case EQUIPPED: {
                renderRod(0.20f, 1.2f, 0.075f, itemStack.getItemDamage());
                break;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                renderRod(0.01f, 2.0f, 1.0f, itemStack.getItemDamage());
                break;
            }
            
            case INVENTORY: {
                renderRod(0.0f, 0.0f, 0.0f, itemStack.getItemDamage());
                break;
            }
            
            default:
                break;
        }
    }

    private void renderRod(float x, float y, float z, int itemDamage) {
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE);
        
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        model.render(.0625f);
        GL11.glPopMatrix();
        
    }
    
    
}
