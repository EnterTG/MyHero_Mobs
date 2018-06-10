package Mobs.Monster.Swiming;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.utils.Utils;

public class Guardian extends de.kniffo80.mobplugin.entities.monster.swimming.Guardian {

	public static final int NETWORK_ID = 49;

	public Guardian(FullChunk chunk, CompoundTag nbt) {
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
			int prismarineShard = Utils.rand(0, 3); // drops 0-2 prismarine shard
			for (int i=0; i < prismarineShard; i++) {
				drops.add(Item.get(Item.PRISMARINE_SHARD, 0, 1));
			}
		}
		return drops.toArray(new Item[drops.size()]);
	}

	@Override
	public int getKillExperience() {
		return 10;
	}

}
