package Mobs.Animal.Walking;

import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class PolarBear extends de.kniffo80.mobplugin.entities.animal.walking.PolarBear {

    public static final int NETWORK_ID = 28;

    public PolarBear(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        return new Item[]{Item.get(Item.RAW_FISH), Item.get(Item.RAW_SALMON)};
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 3);
    }

}
