package com.newlinegaming.Runix.utils;

import java.util.HashSet;

import net.minecraft.world.World;

import com.newlinegaming.Runix.WorldXYZ;

public class Util_SphericalFunctions {

    public static boolean radiusCheck(int x, int y, int z, int rd) {
        return ((x * x) + (y * y) + (z * z) < ((rd + 0.5) * (rd + 0.5)));
    }
    
	public static HashSet<WorldXYZ> getSphere (WorldXYZ coords, int radius)
	{
	    float r_squared = (float)((radius + 0.5) * (radius + 0.5));
		World world = coords.getWorld();
		HashSet<WorldXYZ> returnvalues = new HashSet<WorldXYZ>();
		//loop needs to cap at the top and bottom of the world
		int bottom = Math.max(-radius - 1,  -1*(coords.posY - 1));
		int top = Math.min(radius + 1, (255 - coords.posY));
		for (int y = bottom; y < top; y++)  {
		    for (int z = -radius-1; z < radius+1; z++){
		        for (int x = -radius-1; x < radius+1; x++)
		        {
		            if((x * x) + (y * y) + (z * z) < r_squared)
		            {
		                returnvalues.add(new WorldXYZ(world, coords.posX + x, coords.posY + y, coords.posZ + z));
		            }
		        }
		    }
		}
		return returnvalues;
	}
	
	public static HashSet<WorldXYZ> getShell(WorldXYZ center, int radius){
	    //Josiah: I wrote this so it's probably got holes...
	    HashSet<WorldXYZ> bigSphere = getSphere(center, radius);
	    HashSet<WorldXYZ> smallerSphere = getSphere(center, radius-1);
        bigSphere.removeAll(smallerSphere);
	    return bigSphere;
	}
}
