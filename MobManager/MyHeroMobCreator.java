package MobManager;



import Core.MyHeroMain;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;


public class MyHeroMobCreator implements AbstractMobOption
{
	private MyHeroMob Entity;
	//private String Name;
	
	private AbstractMobOption Options;
	
	public MyHeroMobCreator(int EntityType)
	{
		//Position pos = new Position(0, 0, 0, MyHeroMain.Main.getServer().getDefaultLevel());
		//Location loc = new Location(1395,90,3872,0,0);
		//this.Entity = new MyHeroMob(pos.getLevel().getChunk(pos.getFloorX(), pos.getFloorZ()), MyHeroMob.getDefaultNBT(pos)); 
		//MyHeroMob.createEntity(EntityType,new Position(0,0,0, MyHeroMain.Main.getServer().getDefaultLevel()));
		//System.out.print("Stworzylem: " + this.Entity);
	}
	
	public void SpawnEntity(Location location)
	{
		
		Entity =  new MyHeroMob(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), MyHeroMob.getDefaultNBT(location)); 
		Options.executeMobOption();
		
		Entity.spawnToAll();
		MyHeroMain.Main.getLogger().info("Location: " + Entity.getLocation().toString());
		MyHeroMain.Main.mobmanager.addMob(Entity);
		
		/*MyHeroMob e = ((MyHeroMob)this.Entity.clone());
		e.x = location.x;
		e.y = location.y;
		e.z = location.z;
		e.scheduleUpdate();
		e.despawnFromAll();
		e.spawnToAll();
		MyHeroMain.Main.getLogger().info("Location: " + e.getLocation().toString());*/
		//this.Entity.cl.teleport(location);
	}
	
	
	
	
	
	
	
	@Override
	public void setOption(AbstractMobOption a)
	{
		this.Options = a;
	}
	public void addMobOption(String... value)
	{
		
	}
	
	@Override
	public Entity getEntity()
	{
		return Entity;
	}

	@Override
	public AbstractMobOption getMobOptionList() {
		return Options;
	}

	@Override
	public void executeMobOption()
	{
		
		
	}

	@Override
	public MyHeroMobCreator getRoot() {
		return this;
	}
	
	
	
}
