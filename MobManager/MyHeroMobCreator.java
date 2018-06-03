package MobManager;

import Core.MyHeroMain;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;


public class MyHeroMobCreator implements AbstractMobOption
{
	public MyHeroMob Entity;
	//private String Name;
	
	public MyHeroMobCreator(int EntityType)
	{
		Location loc = new Location(0,0,0,0,0);
		MyHeroMob.createEntity(EntityType,new Position(0,0,0, MyHeroMain.Main.getServer().getDefaultLevel()));
		//System.out.print("Stworzylem: " + this.Entity);
	}
	
	public void SpawnEntity(Level World, Location location)
	{
		

		
	}
	
	
	public void addMobOption(String... value)
	{
	
	}
	
	@Override
	public Entity getEntity()
	{
		return Entity;
	}
	
	
	
}
