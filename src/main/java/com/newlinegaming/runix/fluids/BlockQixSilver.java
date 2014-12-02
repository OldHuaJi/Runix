package com.newlinegaming.runix.fluids;

import com.newlinegaming.runix.RunixMain;
import com.newlinegaming.runix.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockQixSilver extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    private IIcon FQS; //Still Icon for quicksilver

    @SideOnly(Side.CLIENT)
    private IIcon FQF; //Flowing Icon for quicksilver

    public BlockQixSilver(Fluid fluid, Material material) {
        super(fluid, material);
        this.setCreativeTab(RunixMain.TabRunix);
        this.setBlockName("runix:qixsilver");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1)? FQS : FQF;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        FQS = reg.registerIcon(LibInfo.MOD_ID + ":qixsilverstill");
        FQF = reg.registerIcon(LibInfo.MOD_ID + ":qixsilverflowing");
    }
}
