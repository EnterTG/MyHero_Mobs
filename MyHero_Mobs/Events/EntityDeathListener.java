package MyHero_Mobs.Events;

import MyHero_Core.Core.MyHeroMain;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

public class EntityDeathListener implements Listener{

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{
		//LangManager.Log("Is MyHeroMob : " + MyHeroMain.getMyHeroData().getDataMobs().isMyHeroMob(e.getEntity().getId()));
		if(!MyHeroMain.getMyHeroData().MyHeroMobs) return;
		if(MyHeroMain.getMyHeroData().getDataMobs().isMyHeroMob(e.getEntity().getId()))
		{
			if(MyHeroMain.getMyHeroData().MyHeroItems)
			{
				Item[] Drop = MyHeroMain.getMyHeroData().getDataMobs().getMyHeroMobInstance(e.getEntity().getId()).getDrop();
				Location loc = e.getEntity().getLocation();
				Level w = loc.getLevel();
				for(Item i : Drop)
				{
					w.dropItem(loc, i);
					//LangManager.Log("Drop: " + i );
				}
				e.setDrops(Drop);
			}
			
			MyHeroMain.getMyHeroData().getDataMobs().removeSpawnedMyHeroMob(e.getEntity().getId());
		}
	}
}
