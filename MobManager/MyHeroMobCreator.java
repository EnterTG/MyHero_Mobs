package MobManager;

import Core.MyHeroMain;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;


public class MyHeroMobCreator implements AbstractMobOption
{
	public MyHeroMob Entity;
	//private String Name;
	
	public MyHeroMobCreator(int EntityType)
	{
		Location loc = new Location(0,0,0,0,0);
		this.Entity = new MyHeroMob( MyHeroMain.Main.getServer().getDefaultLevel().getChunk(0,0), new CompoundTag()
				.putList(new ListTag<DoubleTag>("Pos")
					.add(new DoubleTag("", loc.x))
					.add(new DoubleTag("", loc.y))
					.add(new DoubleTag("", loc.z)))
				.putList(new ListTag<DoubleTag>("Motion")
					.add(new DoubleTag("", 0))
					.add(new DoubleTag("", 0))
					.add(new DoubleTag("", 0)))
				.putList(new ListTag<FloatTag >("Rotation")
					.add(new FloatTag("", (float) (loc != null ? loc.getYaw() : 0)))
					.add(new FloatTag("", (float) (loc != null ? loc.getPitch() : 0)))));//MyHeroMob.createEntity(EntityType,new Position(0,0,0, MyHeroMain.Main.getServer().getDefaultLevel()));
		//System.out.print("Stworzylem: " + this.Entity);
	}
	
	public void SpawnEntity(Level World, Location location)
	{
		MyHeroMob ent = ( MyHeroMob ) this.Entity.clone();
		ent.setPosition(location.add(0,2,0));
		ent.setLevel(World);
		ent.spawnToAll();
		ent.BaseTick();
		
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
