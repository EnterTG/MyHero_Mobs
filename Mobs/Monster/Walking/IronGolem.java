package Mobs.Monster.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class IronGolem extends de.kniffo80.mobplugin.entities.monster.walking.IronGolem {

    public static final int NETWORK_ID = 20;

    public IronGolem(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.setFriendly(true);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int ironIngots = Utils.rand(3, 6); // drops 3-5 iron ingots
            int poppies = Utils.rand(0, 3); // drops 0-2 poppies
            for (int i=0; i < ironIngots; i++) {
                drops.add(Item.get(Item.IRON_INGOT, 0, 1));
            }
            for (int i=0; i < poppies; i++) {
                drops.add(Item.get(Item.POPPY, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience () {
        return 0;
    }


}
