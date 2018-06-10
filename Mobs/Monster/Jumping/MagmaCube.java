package Mobs.Monster.Jumping;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class MagmaCube extends de.kniffo80.mobplugin.entities.monster.jumping.MagmaCube {

    public static final int NETWORK_ID = 42;

    public MagmaCube(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int magmaCream = Utils.rand(0, 2);
            for (int i = 0; i < magmaCream; i++) {
                drops.add(Item.get(Item.MAGMA_CREAM, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience () {
        return 4;
    }

}
