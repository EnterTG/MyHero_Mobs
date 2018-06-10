package Mobs.Animal.Walking;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class Villager extends de.kniffo80.mobplugin.entities.animal.walking.Villager {

    public static final int NETWORK_ID = 15;

    public Villager(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public int getKillExperience() {
        return 0;
    }
}
