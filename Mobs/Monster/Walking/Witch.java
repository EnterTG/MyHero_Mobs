package Mobs.Monster.Walking;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class Witch extends de.kniffo80.mobplugin.entities.monster.walking.Witch {

    public static final int NETWORK_ID = 45;

    private static final int ATTACK_TICKS = 20; // how many ticks does the witch need to attack

    public Witch(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
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
    public int getKillExperience() {
        return 5; // gain 5 experience
    }

}
