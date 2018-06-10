package Mobs.Animal.Flying;

import cn.nukkit.entity.EntityCreature;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class Bat extends de.kniffo80.mobplugin.entities.animal.flying.Bat {

    public static final int NETWORK_ID = 19;
    
    public Bat(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    private String Drop;
    public void setDrop(String s) {Drop = s;} 
    
    @Override
    public Item[] getDrops() {
        return new Item[0];
    }

    @Override
    public int getKillExperience() {
        return 0;
    }

}
