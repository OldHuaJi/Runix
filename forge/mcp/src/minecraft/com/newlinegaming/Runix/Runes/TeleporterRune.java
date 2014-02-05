package com.newlinegaming.Runix.Runes;

import java.util.ArrayList;

import com.newlinegaming.Runix.NotEnoughRunicEnergyException;
import com.newlinegaming.Runix.PersistentRune;
import com.newlinegaming.Runix.Signature;
import com.newlinegaming.Runix.WorldXYZ;

import net.minecraft.entity.player.EntityPlayer;

public class TeleporterRune extends PersistentRune {

    private static ArrayList<PersistentRune> energizedTeleporters = new ArrayList<PersistentRune>();
    
    public TeleporterRune(){
        super();
        runeName = "Teleporter";
    }
    
    public TeleporterRune(WorldXYZ coords, EntityPlayer activator){
        super(coords, activator,"Teleporter");
        energy = 1;
    }

	public int[][][] runicTemplateOriginal(){
		return new int[][][]
				{{{NONE,TIER,SIGR,TIER,NONE},
				  {TIER,TIER,TIER,TIER,TIER},
				  {SIGR,TIER,KEY ,TIER,SIGR},
				  {TIER,TIER,TIER,TIER,TIER},
				  {NONE,TIER,SIGR,TIER,NONE}}};
	}
	
	
    @Override
    /**Teleport the player to the WaypointRune with a matching signature
     */
    protected void poke(EntityPlayer poker, WorldXYZ coords) {
        consumeKeyBlock(coords);
	    
	    Signature signature = new Signature(this, coords);
	    WorldXYZ destination;
	    //This is necessary because getActiveMagic() CANNOT be static, so it returns a pointer to a static field...
	    ArrayList<PersistentRune> waypointList = (new WaypointRune().getActiveMagic());
	    System.out.println("waypointList.size()" + waypointList.size());
	    PersistentRune wp = null;
	    for( PersistentRune candidate : waypointList){
	        if( new Signature(candidate, candidate.location).equals( signature ) ){
	            wp = candidate;
	            break;
	        }
	    }
	    if( wp == null){
	        aetherSay(poker, "A waypoint with that signature cannot be found.");
	        return;
	    }
	    destination = new WorldXYZ(wp.location);
		try {
            teleportPlayer(poker, destination);
        } catch (NotEnoughRunicEnergyException e) {
            reportOutOfGas(poker);
        }
	}
	
    public String getRuneName() {
        return this.runeName;
	}

    @Override
    public ArrayList<PersistentRune> getActiveMagic() {
        return energizedTeleporters;
    }

    public boolean oneRunePerPerson() {
        return false;
    }

    @Override
    public boolean isFlatRuneOnly() {
        return false;
    }

}