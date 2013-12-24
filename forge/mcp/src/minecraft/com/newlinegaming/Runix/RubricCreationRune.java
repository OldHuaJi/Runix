package com.newlinegaming.Runix;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//
//import argo.jdom.JdomParser;
//import argo.jdom.JsonNode;
//import argo.jdom.JsonRootNode;
//import argo.saj.InvalidSyntaxException;
//
//import net.minecraft.block.Block;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.nbt.NBTTagString;
//import net.minecraft.world.World;

public class RubricCreationRune extends AbstractRune{
  
    public static ArrayList<RubricCreationRune> storedPatterns=new ArrayList<RubricCreationRune>();
    public HashMap<WorldXYZ, SigBlock> structure;
    public WorldXYZ anchorpoint;
    
    public RubricCreationRune(){}
    public RubricCreationRune(HashMap<WorldXYZ, SigBlock> building, WorldXYZ location){
	
	structure = building;
	anchorpoint = location;
    }


    @Override
    public int[][][] blockPattern() {
	int RT=Block.torchRedstoneActive.blockID;
	return new int [][][] 
	            {{{0,   TIER,0, TIER   ,0},
	              {TIER,TIER,RT,TIER,TIER},
	              {0,     RT,TIER,RT,   0},
	              {TIER,TIER,RT,TIER,TIER},
	              {0,   TIER,0, TIER,   0}
	             }}; 
	
    }

    @Override
    public void execute(EntityPlayer player, WorldXYZ coords) {
	accept(player);
	HashMap<WorldXYZ, SigBlock> structure=conductanceStep(coords, 50);
	storedPatterns.add(new RubricCreationRune(structure, coords));
	moveShape(structure, 0, 20, 0);
	for(WorldXYZ XYZ : structure.keySet())
	{
	   	    //note this is just for visual effect, at least for the time being
	    XYZ.setBlockId(0);
	    
	}
    }

    @Override
    public String getRuneName() { return "RubricCreationRune";

    }

}