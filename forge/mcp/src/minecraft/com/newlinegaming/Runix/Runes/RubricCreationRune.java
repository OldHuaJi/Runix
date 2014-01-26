package com.newlinegaming.Runix.Runes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import com.newlinegaming.Runix.BlockRecord;
import com.newlinegaming.Runix.PersistentRune;
import com.newlinegaming.Runix.RenderHelper;
import com.newlinegaming.Runix.Signature;
import com.newlinegaming.Runix.Vector3;
import com.newlinegaming.Runix.WorldXYZ;


public class RubricCreationRune extends PersistentRune {

	private static ArrayList<PersistentRune> storedPatterns = new ArrayList<PersistentRune>();
	public Signature sig = null;
	public ArrayList<BlockRecord> structure = new ArrayList<BlockRecord>();
	protected transient RenderHelper renderer = null;

    public RubricCreationRune() {
        runeName = "Rubric Creator";
    }

    public RubricCreationRune(WorldXYZ coords, EntityPlayer player2) 
    {
	    super(coords, player2,"Rubric Creator");
	}
    
    protected void initializeRune(){
        renderer = new RenderHelper();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private ArrayList<BlockRecord> scanStructure(HashSet<WorldXYZ> shape) {
        ArrayList<BlockRecord> fullData = new ArrayList<BlockRecord>();
        for(WorldXYZ point : shape){
            Vector3 offset = new Vector3(location, point);
            fullData.add(new BlockRecord(1, offset, point.getSigBlock()));
        }
        return fullData;
    }

    @Override
	public int[][][] runicTemplateOriginal() {
		int RTCH = Block.torchRedstoneActive.blockID;
		return new int[][][] 
		      {{{ NONE,TIER,SIGR,TIER,NONE },
				{ TIER,TIER,RTCH,TIER,TIER },
				{ SIGR,RTCH,KEY ,RTCH,SIGR },
				{ TIER,TIER,RTCH,TIER,TIER },
				{ NONE,TIER,SIGR,TIER,NONE }}};
	}


	@Override
	protected void poke(EntityPlayer poker, WorldXYZ coords){
	    if( renderer == null)
	        initializeRune();
		renderer.reset();
		HashSet<WorldXYZ> shape = conductanceStep(coords, 50);
		structure = scanStructure(shape);
		ItemStack toolused = poker.getCurrentEquippedItem();
		sig = new Signature(this, coords);
				//TODO check for signature collision


		if (toolused != null && toolused.itemID == Item.book.itemID) {

			consumeRune(location);//need to remove the rune itself add runic energy
			structure = scanStructure(shape);//then capture everything else into the rubric file 
			consumeRune(extractCoordinates(structure));

	        specialName = toolused.getDisplayName();
			aetherSay(poker, "the tool used is "+ specialName);


			//rename the book to something we can identify the book with the recall

        }
        
	}
	
	
	private Collection<WorldXYZ> extractCoordinates(Collection<BlockRecord> structureRecord) {
	    ArrayList<WorldXYZ> blocks = new ArrayList<WorldXYZ>();
	    for( BlockRecord record : structureRecord )
	        blocks.add(location.offset(record.offset));
        return blocks;
    }

    @ForgeSubscribe
	public void renderWireframe(RenderWorldLastEvent evt) {
		if (getPlayer() != null)
			renderer.highlightBoxes(extractCoordinates(structure), false, getPlayer(), 221, 0, 0);//TODO this is really slow for every frame
	}
	
    @Override
    public void loadRunes() {
        super.loadRunes();
        System.out.println("Rubrics: " + getActiveMagic().size() + "\n    " + getActiveMagic());
    }	

    @Override
    public ArrayList<PersistentRune> getActiveMagic() {
        return storedPatterns;
    }

    @Override
    public boolean oneRunePerPerson() {
        return false;
    }
    
    public boolean isFlatRuneOnly() {
        return false; //TODO: Consider this
    }
}
