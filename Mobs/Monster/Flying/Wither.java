package Mobs.Monster.Flying;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Wither extends de.kniffo80.mobplugin.entities.monster.flying.Wither {

    public static final int NETWORK_ID = 52;

    public Wither(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public int getKillExperience() {
        return 50;
    }


    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int netherstar = Utils.rand(0, 101) <= 3 ? 1 : 0;
            for (int i = 0; i < netherstar; i++) {
                drops.add(Item.get(Item.NETHER_STAR, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

}
