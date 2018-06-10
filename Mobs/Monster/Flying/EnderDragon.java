package Mobs.Monster.Flying;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EnderDragon extends de.kniffo80.mobplugin.entities.monster.flying.EnderDragon {

    public static final int NETWORK_ID = 53;

    public EnderDragon(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        return new Item[]{Item.get(Item.DRAGON_EGG), Item.get(410)};
    }

    @Override
    public int getKillExperience() {
        return 500;
    }

}
