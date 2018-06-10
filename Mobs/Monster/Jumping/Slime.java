package Mobs.Monster.Jumping;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Slime extends de.kniffo80.mobplugin.entities.monster.jumping.Slime {

    public static final int NETWORK_ID = 37;

    public Slime(FullChunk chunk, CompoundTag nbt) {
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
            int slimeBalls = Utils.rand(0, 3);
            for (int i = 0; i < slimeBalls; i++) {
                drops.add(Item.get(Item.SLIMEBALL, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience () {
        return 4;
    }

}
