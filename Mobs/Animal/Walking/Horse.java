package Mobs.Animal.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

/**
 * Implementation of a horse
 *
 * @author <a href="mailto:kniffman@googlemail.com">Michael Gertz</a>
 */
public class Horse extends de.kniffo80.mobplugin.entities.animal.walking.Horse {

    public static final int NETWORK_ID = 23;
    private int Type = 0;
    private int Variant = this.getRandomVariant();

    public Horse(FullChunk chunk, CompoundTag nbt) {
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

            for (int i = 0; i < leather; i++) {
                drops.add(Item.get(Item.LEATHER, 0, 1));
            }
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 4);
    }


    private int getRandomVariant(){
        int VariantList[] = {
                0,1,2,3,4,5,6,
                256,257,258,259,260,261,262,
                512,513,514,515,516,517,518,
                768,769,770,771,772,773,774,
                1024,1025,1026,1027,1028,1029,1030
        };
        return VariantList[Utils.rand(0,VariantList.length)];
    }
}
