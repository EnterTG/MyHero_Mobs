package Mobs.Animal.Swiming;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.entity.EntityCreature;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Dolphin extends de.kniffo80.mobplugin.entities.animal.swimming.Dolphin {

    public static final int NETWORK_ID = 31;

    public Dolphin(FullChunk chunk, CompoundTag nbt) {
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
            int fish = Utils.rand(0, 2);
            for (int i = 0; i < fish; i++) {
                drops.add(Item.get(Item.RAW_FISH, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return 0;
    }
}
