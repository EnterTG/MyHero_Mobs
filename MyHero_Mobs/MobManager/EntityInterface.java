package MyHero_Mobs.MobManager;

import cn.nukkit.entity.Entity;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public interface EntityInterface {

	public Entity getEntity(FullChunk c,CompoundTag tag);
}
