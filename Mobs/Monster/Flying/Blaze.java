package Mobs.Monster.Flying;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Blaze extends de.kniffo80.mobplugin.entities.monster.flying.Blaze {

    public static final int NETWORK_ID = 43;

    public Blaze(FullChunk chunk, CompoundTag nbt) {
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
            int blazeRod = Utils.rand(0, 2); // drops 0-1 blaze rod
            int glowStoneDust = Utils.rand(0, 3); // drops 0-2 glowstone dust
            for (int i = 0; i < blazeRod; i++) {
                drops.add(Item.get(Item.BLAZE_ROD, 0, 1));
            }
            for (int i = 0; i < glowStoneDust; i++) {
                drops.add(Item.get(Item.GLOWSTONE_DUST, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return 10; // gain 10 experience
    }

}
