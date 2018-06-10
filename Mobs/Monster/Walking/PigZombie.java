package Mobs.Monster.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemSwordGold;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.MobEquipmentPacket;
import de.kniffo80.mobplugin.route.WalkerRouteFinder;
import de.kniffo80.mobplugin.utils.Utils;

public class PigZombie extends de.kniffo80.mobplugin.entities.monster.walking.PigZombie
{

    public static final int NETWORK_ID = 36;

    int                     angry      = 0;

    public PigZombie(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.route = new WalkerRouteFinder(this);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public void spawnTo(Player player) {
        super.spawnTo(player);

        MobEquipmentPacket pk = new MobEquipmentPacket();
        pk.eid = this.getId();
        pk.item = new ItemSwordGold();
        pk.inventorySlot = 0;
        player.dataPacket(pk);
    }

    @Override
    public Item[] getDrops() {
        List<Item> drops = new ArrayList<>();
        if (this.lastDamageCause instanceof EntityDamageByEntityEvent) {
            int rottenFlesh = Utils.rand(0, 2); // drops 0-1 rotten flesh
            int goldNuggets = Utils.rand(0, 101) <= 3 ? 1 : 0; // with a 2,5% chance a gold nugget is dropped
            int goldSword = Utils.rand(0, 101) <= 9 ? 1 : 0; // with a 8,5% chance it's gold sword is dropped
            for (int i=0; i < rottenFlesh; i++) {
                drops.add(Item.get(Item.ROTTEN_FLESH, 0, 1));
            }
            for (int i=0; i < goldNuggets; i++) {
                drops.add(Item.get(Item.GOLD_NUGGET, 0, 1));
            }
            for (int i=0; i < goldSword; i++) {
                drops.add(Item.get(Item.GOLD_SWORD, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience () {
        return 5; // gain 5 experience
    }

}
