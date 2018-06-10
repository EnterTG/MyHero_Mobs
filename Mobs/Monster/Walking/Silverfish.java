package Mobs.Monster.Walking;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.route.WalkerRouteFinder;

public class Silverfish extends de.kniffo80.mobplugin.entities.monster.walking.Silverfish {

    public static final int NETWORK_ID = 39;

    public Silverfish(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.route = new WalkerRouteFinder(this);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }



    @Override
    public Item[] getDrops() {
        return new Item[0];
    }

    @Override
    public int getKillExperience () {
        return 5; // gain 5 experience
    }


}
