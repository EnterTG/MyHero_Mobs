package Mobs.Animal.Walking;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Ocelot extends de.kniffo80.mobplugin.entities.animal.walking.Ocelot {

    public static final int NETWORK_ID = 22;

    public Ocelot(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    public Item[] getDrops() {
        return new Item[0];
    }

    public int getKillExperience() {
        return Utils.rand(1, 4); // gain 1-3 experience
    }

}
