package Events;

import MobManager.MobManager;
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
		if(MobManager.AllSpawnedMobs.containsKey(e.getEntity().getId()))
		{
			
			Item[] Drop = MobManager.AllSpawnedMobs.get(e.getEntity().getId()).getDrop();
			Location loc = e.getEntity().getLocation();
			Level w = loc.getLevel();
			for(Item i : Drop)
			{
				w.dropItem(loc, i);
				//MyHeroMain.Main.getLogger().info("Drop: " + i );
			}
			e.setDrops(Drop);
			
		}
	}
}
