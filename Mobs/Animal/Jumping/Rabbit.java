package Mobs.Animal.Jumping;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Rabbit extends de.kniffo80.mobplugin.entities.animal.jumping.Rabbit {

    public static final int NETWORK_ID = 18;

    public Rabbit(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public Item[] getDrops() {
        if (this.isBaby()) {

        }
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int rabbitHide = Utils.rand(0, 2); // drops 0-1 rabit hide
            int rawRabbit = Utils.rand(0, 2); // drops 0-1 raw rabit
            int rabbitfoot = Utils.rand(0, 101) <= 9 ? 1 : 0; //8.5%
            for (int i = 0; i < rabbitHide; i++) {
                drops.add(Item.get(Item.RABBIT_HIDE, 0, 1));
            }
            for (int i = 0; i < rabbitfoot; i++) {
                drops.add(Item.get(Item.RABBIT_FOOT, 0, 1));
            }
            for (int i = 0; i < rawRabbit; i++) {
                drops.add(Item.get(this.isOnFire() ? Item.COOKED_RABBIT : Item.RAW_RABBIT, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 4); // gain 1-3 experience
    }

}
