package Mobs.Monster.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.route.WalkerRouteFinder;
import de.kniffo80.mobplugin.utils.Utils;

public class Evoker extends de.kniffo80.mobplugin.entities.monster.walking.Evoker {

    public static final int NETWORK_ID = 104;

    public Evoker(FullChunk chunk, CompoundTag nbt) {
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
            int emerald = Utils.rand(0, 2);
//            int totem = 1;
            for (int i=0; i < emerald; i++) {
                drops.add(Item.get(Item.EMERALD, 0, 1));
            }
//            for (int i=0; i < totem; i++) {
//                drops.add(Item.get(Item.TOTEM_OF_UNDYING, 0, 1));
//            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience () {
        return 10;
    }

}
