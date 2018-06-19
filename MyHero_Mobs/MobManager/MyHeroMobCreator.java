package MyHero_Mobs.MobManager;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import MyHero_Mobs.DropManager.DropManager;
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
	public void setDropList(String s)
	{
		if(DropsNames == null)
			DropsNames = new ArrayList<>();
		DropsNames.add(s);
		
	}
	public void SpawnEntity(Location location)
	{
		Entity = MobManager.MobsInterface.get(getType()).getEntity(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), cn.nukkit.entity.Entity.getDefaultNBT(location));
		
		//Entity =  new MyHeroMob(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), MyHeroMob.getDefaultNBT(location)); 
		this.Options.executeMobOption();
		MobManager.AllSpawnedMobs.put(Entity.getId(), this);
		Entity.spawnToAll();
	}
	public void SpawnEntity(Vector3 location,Level l)
	{
		Entity = MobManager.MobsInterface.get(getType()).getEntity(l.getChunk(location.getChunkX(), location.getChunkZ()), cn.nukkit.entity.Entity.getDefaultNBT(location));
		
		//Entity =  new MyHeroMob(location.getLevel().getChunk(location.getChunkX(), location.getChunkZ()), MyHeroMob.getDefaultNBT(location)); 
		this.Options.executeMobOption();
		MobManager.AllSpawnedMobs.put(Entity.getId(), this);
		Entity.spawnToAll();
	}
	
	public Item[] getDrop()
	{
		Item[] both = new Item[0];
		if(DropsNames != null)
			for(String dropstring : DropsNames)
			{
				if(DropManager.AllDrops.containsKey(dropstring))
					both = Stream.concat(Arrays.stream(both), Arrays.stream(DropManager.AllDrops.get(dropstring).getDrop())).toArray(Item[]::new);
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

	public MobsType getType() {
		return type;
	}

	public String getFileName() {
		return FileName;
	}


	
	
	
}
