package Mobs.Animal.Walking;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Chicken extends de.kniffo80.mobplugin.entities.animal.walking.Chicken {

    public static final int NETWORK_ID = 10;

    private int EggLayTime = this.getRandomEggLayTime();
    private boolean IsChickenJockey = false;

    public Chicken(FullChunk chunk, CompoundTag nbt) {
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
            int featherDrop = Utils.rand(0, 3); // drops 0-2 feathers
            for (int i = 0; i < featherDrop; i++) {
                drops.add(Item.get(Item.FEATHER, 0, 1));
            }
            // chicken on fire: cooked chicken otherwise raw chicken
            drops.add(Item.get(this.isOnFire() ? Item.COOKED_CHICKEN : Item.RAW_CHICKEN, 0, 1));
        }
        return drops.toArray(new Item[drops.size()]);
    }

    @Override
    public int getKillExperience() {
        return Utils.rand(1, 4); // gain 1-3 experience
    }

    private int getRandomEggLayTime(){
        return Utils.rand(6000,12000);
    }

    public boolean isChickenJockey() {
        return IsChickenJockey;
    }

    public void setChickenJockey(boolean chickenJockey) {
        IsChickenJockey = chickenJockey;
    }
}
