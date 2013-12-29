package com.newlinegaming.Runix;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;

public class Util_Movement {

    public static HashMap<WorldXYZ, WorldXYZ> xzRotation(Collection<WorldXYZ> startingShape, WorldXYZ centerPoint, boolean counterClockwise){
        //centerPoint is also the axis of rotation
        HashMap<WorldXYZ, WorldXYZ> rotationMapping = new HashMap<WorldXYZ, WorldXYZ>();
        for( WorldXYZ point : startingShape ){
            rotationMapping.put(point, point.rotate(centerPoint, counterClockwise));//flip sign on X
        }
        return rotationMapping;
    }
    
    
    /** Note: when designing moving runes, DO NOT update your PersistentRune.location variable.  
     * moveShape() calls moveMagic() which will update everything including yourself.
     * @param vehicleBlocks
     * @param dX
     * @param dY
     * @param dZ
     * @return
     */
    public static HashMap<WorldXYZ, SigBlock> moveShape(HashMap<WorldXYZ, SigBlock> vehicleBlocks, int dX, int dY, int dZ) {
        //Josiah: If you're having trouble with glitches, try only running it on the server side
        //if( !world.isRemote ) //this is only true server side
        HashMap<WorldXYZ, SigBlock> sensitiveBlocks = new HashMap<WorldXYZ, SigBlock>();
        for(Entry<WorldXYZ, SigBlock> pair : vehicleBlocks.entrySet()){
            if( Tiers.isMoveSensitive(pair.getValue().blockID) ){
                sensitiveBlocks.put(pair.getKey().offset(dX, dY, dZ), pair.getValue());
                pair.getKey().setBlockId(0);
            }//Josiah: Hopefully this isn't too slow.  I coulddn't find a shorter path to preserving these blocks
        }
        
        for(WorldXYZ loc : vehicleBlocks.keySet())
            loc.setBlockId(0); // delete old block in a separate loop to avoid collisions

        HashMap<WorldXYZ, SigBlock> newPositions = new HashMap<WorldXYZ, SigBlock>();
        for(WorldXYZ start : vehicleBlocks.keySet()){
            WorldXYZ target = start.offset(dX, dY, dZ);
            SigBlock sig = vehicleBlocks.get(start);
            if( !Tiers.isMoveSensitive(sig.blockID) )
                target.setBlockId(sig);
            newPositions.put(target, sig);
        }
        for(WorldXYZ specialPos : sensitiveBlocks.keySet()) //blocks like torches and redstone
            specialPos.setBlockId(sensitiveBlocks.get(specialPos)); 

      RuneHandler.getInstance().moveMagic(vehicleBlocks.keySet(), dX, dY, dZ);
        return newPositions;
    }

    /**See notes on moveShape*/
    public static HashMap<WorldXYZ, SigBlock> rotateShape(HashMap<WorldXYZ, WorldXYZ> moveMapping) 
    {
        HashMap<WorldXYZ, SigBlock> oldStructure = new HashMap<WorldXYZ, SigBlock>();
        for(WorldXYZ p : moveMapping.keySet()){
            oldStructure.put(p, p.getSigBlock());//record original before we mess it up
        }
        
        HashMap<WorldXYZ, SigBlock> sensitiveBlocks = new HashMap<WorldXYZ, SigBlock>();
        for(WorldXYZ startingPosition : moveMapping.keySet()){
            SigBlock block = startingPosition.getSigBlock();
            if( Tiers.isMoveSensitive(block.blockID) ){
                sensitiveBlocks.put(moveMapping.get(startingPosition)  , block);
                startingPosition.setBlockId(0);//delete sensitive blocks first to prevent drops
            }
        }
        
        for(WorldXYZ loc : moveMapping.keySet())
            loc.setBlockId(0); // delete old block in a separate loop to avoid collisions with the new positioning

        HashMap<WorldXYZ, SigBlock> newStructure = new HashMap<WorldXYZ, SigBlock>();
        for(WorldXYZ start : oldStructure.keySet()){
            WorldXYZ target = moveMapping.get(start);
            SigBlock sig = oldStructure.get(start);
            if( !Tiers.isMoveSensitive(sig.blockID) )
                target.setBlockId(sig);
            newStructure.put(target, sig);
        }
        for(WorldXYZ specialPos : sensitiveBlocks.keySet()) //blocks like torches and redstone
            specialPos.setBlockId(sensitiveBlocks.get(specialPos)); 
        
        RuneHandler.getInstance().moveMagic(moveMapping);
        return newStructure;
    }

    /**Geometry: figure out if we're on the left or right side of the rune relative to the player
     */
    public static boolean lookingRightOfCenterBlock(EntityPlayer player, WorldXYZ referencePoint) {
        float yaw = player.rotationYawHead;//assumption: you're looking at the block you right clicked
        yaw = (yaw > 0.0) ? yaw  : yaw + 360.0F; //Josiah: minecraft yaw wanders into negatives sometimes...
        double opposite = player.posZ - referencePoint.posZ - .5;
        double adjacent = player.posX - referencePoint.posX - .5;
        double angle = Math.toDegrees(Math.atan( opposite / adjacent )) + 90.0;
        if( adjacent > 0.0)
            angle += 180.0;
//        System.out.println("Rune: " + angle + "  Yaw: " + yaw + " = " + (angle - yaw));
        if( ((angle - yaw) < 180.0 && (angle - yaw) > 0.0) || //the difference between the angle to the reference
                ((angle - yaw) < -180.0 && (angle - yaw) > -360.0) )//and the angle we're looking determines left/right
            return true;
        else
            return false;
    }


}
