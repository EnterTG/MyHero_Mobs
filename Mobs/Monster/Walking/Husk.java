package Mobs.Monster.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.route.WalkerRouteFinder;
import de.kniffo80.mobplugin.utils.Utils;

public class Husk extends de.kniffo80.mobplugin.entities.monster.walking.Husk {

    public static final int NETWORK_ID = 47;

    public Husk(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.route = new WalkerRouteFinder(this);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int rottenFlesh = Utils.rand(0, 3);
            for (int i = 0; i < rottenFlesh; i++) {
                drops.add(Item.get(Item.ROTTEN_FLESH, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return 5;
    }

}
