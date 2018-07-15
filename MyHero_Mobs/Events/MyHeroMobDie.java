package MyHero_Mobs.Events;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Mobs.MobManager.MyHeroMobCreator;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.item.Item;

public class MyHeroMobDie extends Event implements Cancellable{

	private Entity victim;
	private Entity killer;
	private MyHeroMobCreator myherocreator;
	private Item[] drop;
	private boolean isCancelled;
	
	
	
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlers() 
	{
		return handlers;
	}
	
 	public MyHeroMobDie(Entity victim,Entity killer, MyHeroMobCreator mhm)
	{
 		setKiller(killer);
 		setVictim(victim);
 		myherocreator = mhm;
		if(MyHeroMain.getMyHeroData().MyHeroItems)
			this.setDrop(mhm.getDrop());
		this.setDrop(drop);
	}



	public Entity getVictim() {
		return victim;
	}



	public void setVictim(Entity victim) {
		this.victim = victim;
	}



	public Entity getKiller() {
		return killer;
	}



	public void setKiller(Entity killer) {
		this.killer = killer;
	}



	public MyHeroMobCreator getMyherocreator() {
		return myherocreator;
	}

	public void setMyherocreator(MyHeroMobCreator myherocreator) {
		this.myherocreator = myherocreator;
	}

	public Item[] getDrop() {
		return drop;
	}

	public void setDrop(Item[] drop) {
		this.drop = drop;
	}
	
	@Override
	public boolean isCancelled() {
		return this.isCancelled;
	}
	 
	@Override
	public void setCancelled(boolean arg0) {
		this.isCancelled = arg0;
	}
	
	
}

