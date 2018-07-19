package MyHero_Mobs.Events;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.Managers.LangManager;
import MyHero_Levels.API.MyHeroLevel;
import MyHero_Levels.API.MyHeroLevelsAPI;
import MyHero_Levels.Core.MyHeroMain_Levels;
import MyHero_Levels.Events.MyHeroExpSource;
import MyHero_Mobs.MobManager.MyHeroMobCreator;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import de.kniffo80.mobplugin.entities.animal.Animal;
import de.kniffo80.mobplugin.entities.monster.Monster;

public class EntityDeathListener implements Listener{

	
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{
		if(MyHeroMain.getMyHeroData().getDataMobs().isMyHeroMob(e.getEntity().getId()))
		{
			e.setDrops( new Item[]{Item.get(0) });
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		//if(e.getCause().equals(DamageCause.CUSTOM)) return;
		LangManager.Log("Damage: " + e.getDamage());
		if(e.getEntity().getHealth()- e.getDamage() > 0 ) return;
		
		if(e.getDamager() instanceof Player && !(e.getEntity() instanceof Player) && MyHeroMain.getMyHeroData().getDataMobs().isMyHeroMob(e.getEntity().getId()))
		{
			MyHeroMobCreator mhmc = MyHeroMain.getMyHeroData().getDataMobs().getMyHeroMobInstance(e.getEntity().getId());
			MyHeroMobDie event = new MyHeroMobDie(e.getEntity(),e.getDamager(), mhmc);
			
			MyHeroMain.getMain().getServer().getPluginManager().callEvent(event);
			if(!event.isCancelled())
			{
				MyHeroMain.getMyHeroData().getDataMobs().removeSpawnedMyHeroMob(e.getEntity().getId());
			}
			else
			{
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onMyHeroMobDeathEvent(MyHeroMobDie e)
	{
		//if(!MyHeroMain.getMyHeroData().MyHeroMobs) return;
		if(e.getDrop() != null)
		{
			Item[] Drop = MyHeroMain.getMyHeroData().getDataMobs().getMyHeroMobInstance(e.getVictim().getId()).getDrop();
			Location loc = e.getVictim().getLocation();
			Level w = loc.getLevel();
			for(Item i : Drop)
			{
				w.dropItem(loc, i);
			}
		}
		if(MyHeroMain.getMyHeroData().MyHeroLevels)
		{
			MyHeroLevelsAPI api = MyHeroMain_Levels.getAPI();
			MyHeroLevel mhl = api.getMyHeroLevel((Player)e.getKiller());
			
			mhl.addExp(e.getMyherocreator().getExp(),getMobType(e.getVictim()));
		}
	}
	
	
	private MyHeroExpSource getMobType(Entity e)
	{
		if(e instanceof Monster )
			return MyHeroExpSource.Mob;
		else if(e instanceof Animal)
			return MyHeroExpSource.Animal;
		else
			return MyHeroExpSource.Other;
	}
}
