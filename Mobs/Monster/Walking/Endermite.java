package Mobs.Monster.Walking;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class Endermite extends de.kniffo80.mobplugin.entities.monster.walking.Endermite {

    public static final int NETWORK_ID = 55;

    public Endermite(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public Item[] getDrops() {
        return new Item[0];
    }

    @Override
    public int getKillExperience () {
        return 3;
    }


}
