package Mobs.Animal.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

/**
 * Implementation of a mule
 *
 * @author <a href="mailto:kniffman@googlemail.com">Michael Gertz</a>
 */
public class ZombieHorse extends de.kniffo80.mobplugin.entities.animal.walking.ZombieHorse {

    public static final int NETWORK_ID = 27;

    public ZombieHorse(FullChunk chunk, CompoundTag nbt) {
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
            //https://minecraft.gamepedia.com/Horse
            int leather = Utils.rand(0, 3); // drops 0-2 leather
            int rottenflesh = Utils.rand(0, 3);// drop 0-2 rettenflesh

            for (int i = 0; i < leather; i++) {
                drops.add(Item.get(Item.LEATHER, 0, 1));
            }

            for (int i = 0; i < rottenflesh; i++) {
                drops.add(Item.get(Item.ROTTEN_FLESH, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 4);
    }

}
