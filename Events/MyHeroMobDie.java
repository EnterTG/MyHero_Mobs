package Events;

import cn.nukkit.entity.EntityLiving;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.item.Item;

public class MyHeroMobDie extends EntityDeathEvent{

	
	public MyHeroMobDie(EntityLiving entity, Item[] drop)
	{
		super(entity,drop);
	}
}
