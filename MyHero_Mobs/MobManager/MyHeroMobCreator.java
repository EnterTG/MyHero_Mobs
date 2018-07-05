package MyHero_Mobs.MobManager;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataMobs;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;

public class MyHeroMobCreator implements AbstractMobOption
{
	private Entity Entity;
	private final String FileName;
	private final MobsType type;
	private AbstractMobOption Options;
	private List<String> DropsNames;
	
	public MyHeroMobCreator(String name ,MobsType EntityType)
	{
		type = EntityType;
		FileName = name;
	}
	public void addDrop(String s)
	{
		if(DropsNames == null)
			DropsNames = new ArrayList<>();
		DropsNames.add(s);
		
	}
	public Entity SpawnEntity(Location location)
	{
		if(!MyHeroMain.getMyHeroData().MyHeroMobs) return null;
		DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
		Entity =datamobs.getMobType(getType()).getEntity(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), cn.nukkit.entity.Entity.getDefaultNBT(location));
		
		//Entity =  new MyHeroMob(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), MyHeroMob.getDefaultNBT(location)); 
		this.Options.executeMobOption();
		
		datamobs.addNewSpawnedMyHeroMob(Entity.getId(), Entity);
		datamobs.addNewSpawnedMyHeroMobCreator(Entity.getId(), this);
		
		Entity.spawnToAll();
		Entity.fireTicks = -30;
		return Entity;
	}
	public Entity SpawnEntity(Vector3 location,Level l)
	{
		if(!MyHeroMain.getMyHeroData().MyHeroMobs) return null;
		DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
		Entity =datamobs.getMobType(getType()).getEntity(l.getChunk(location.getChunkX(), location.getChunkZ()), cn.nukkit.entity.Entity.getDefaultNBT(location));
		final long id = Entity.getId();
		//Entity =  new MyHeroMob(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), MyHeroMob.getDefaultNBT(location)); 
		this.Options.executeMobOption();
		datamobs.addNewSpawnedMyHeroMob(id, Entity);
		datamobs.addNewSpawnedMyHeroMobCreator(id, this);
		Entity.spawnToAll();
		Entity.fireTicks = -30;
		return Entity;
	}
	
	public Item[] getDrop()
	{
		Item[] both = new Item[0];
		DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
		if(DropsNames != null)
			for(String dropstring : DropsNames)
			{
				if(datamobs.DropExist(dropstring))
					both = Stream.concat(Arrays.stream(both), Arrays.stream(datamobs.getDrop(dropstring).getDrop())).toArray(Item[]::new);
			}
		return both;
	}
	
	
	/*public Entity getMob(int MobID)
	{
		switch(MobID)
		{
			case 0:
				return null;
		}
	}*/
	
	@Override
	public void setOption(AbstractMobOption a)
	{
		this.Options = a;
	}
	public void addMobOption(Object... value)
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

	public MobsType getType() {
		return type;
	}

	public String getFileName() {
		return FileName;
	}

	@Override
	public String toString() {
		return "MobName: "+FileName+" MobType: "+type.name() +" Drops: " + DropsNames.toString()+System.lineSeparator();
	}
	
	
	
}
