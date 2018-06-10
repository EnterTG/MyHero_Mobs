package Mobs.Animal.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

/**
 * Implementation of a skeleton horse
 *
 * @author <a href="mailto:kniffman@googlemail.com">Michael Gertz</a>
 */
public class SkeletonHorse extends de.kniffo80.mobplugin.entities.animal.walking.SkeletonHorse {

    public static final int NETWORK_ID = 26;

    public SkeletonHorse(FullChunk chunk, CompoundTag nbt) {
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
            int leather = Utils.rand(0, 3); // drops 0-2 leather
            int bone = Utils.rand(0, 2);

            for (int i = 0; i < leather; i++) {
                drops.add(Item.get(Item.LEATHER, 0, 1));
            }

            for (int i = 0; i < bone; i++) {
                drops.add(Item.get(Item.BONE, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 4);
    }

}
