package MyHero_Mobs.RegionsManager;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

public abstract class Region {

	protected static final int MaxRetry = 4;
	protected static final int Density = 1;
	
	protected boolean SpawninRooms = false;
	protected boolean SpawninWater = false;
	
	protected List<Integer> SpawnOn = new ArrayList<Integer>();
	
	protected Level world ; 
	
	public Region()
	{
		SpawnOn.add(1);
		SpawnOn.add(2);
		SpawnOn.add(13);
	}
	
	public abstract boolean playerInRegion(Player p);
	
	public abstract void generateSpawnPoints(List<Vector3> target);
	
	
	
	public Level getWorld() {
		return world;
	}
	public void setWorld(Level world) {
		this.world = world;
	}
	
	public void addSpawnOn(int blockid)
	{
		SpawnOn.add(blockid);
	}

	public boolean isSpawninRooms() {
		return SpawninRooms;
	}

	public void setSpawninRooms(boolean spawninRooms) {
		SpawninRooms = spawninRooms;
	}

	public boolean isSpawninWater() {
		return SpawninWater;
	}

	public void setSpawninWater(boolean spawninWater) {
		SpawninWater = spawninWater;
	}
	
	
}
