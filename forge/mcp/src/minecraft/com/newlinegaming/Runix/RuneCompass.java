package com.newlinegaming.Runix;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class RuneCompass extends AbstractRune{


    RuneCompass(){
        runeName = "Compass";
    }
    
	public int[][][] runicTemplateOriginal(){
		return new int [][][] 
            {{{TIER, 0 ,TIER},
              { 0 ,TIER, 0 },
              {TIER, 0 ,TIER}}}; //This is AIR 0 on purpose
	}

	public void execute(WorldXYZ coords, EntityPlayer player){
	    int ink = getTierInkBlock(coords);
		int[][][] compassOutcome = new int [][][]
				{{{ 0 ,ink, 0 },
				  {ink, 0 ,ink},
				  {ink, 0 ,ink}}};
		coords.overrideFacing(1);
        HashMap<WorldXYZ, SigBlock> stamp = patternToShape(compassOutcome, coords);
		if(stampBlockPattern(stamp, player))
		    accept(player);
	}
	
	public String getRuneName()
	{
		return "Compass";
	}
	
	public boolean isFlatRuneOnly() {
	    return true;
	}

}