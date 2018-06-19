package SpawningMamager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MobManager.MyHeroMobCreator;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

public class Spawner {

	public double getChance() {
		return Chance;
	}

	public void setChance(double chance) {
		Chance = chance;
	}

	public int getMinMobsPerSpawn() {
		return MinMobsPerSpawn;
	}

	public void setMinMobsPerSpawn(int minMobsPerSpawn) {
		MinMobsPerSpawn = minMobsPerSpawn;
	}

	public int getMaxMobsPerSpawn() {
		return MaxMobsPerSpawn;
	}

	public void setMaxMobsPerSpawn(int maxMobsPerSpawn) {
		MaxMobsPerSpawn = maxMobsPerSpawn;
	}

	public LinkedHashMap<MyHeroMobCreator, Integer> getMobs() {
		return Mobs;
	}

	public void setMobs(LinkedHashMap<MyHeroMobCreator, Integer> mobs) {
		Mobs = mobs;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Integer> getSpawnOn() {
		return SpawnOn;
	}

	public void setSpawnOn(List<Integer> spawnOn) {
		SpawnOn = spawnOn;
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	private static final int MaxMobs = 10;
	private static final int MaxRetry = 4;
	private static final int Density = 1;
	private List<Entity> SpawnedMobs;
	
	private double Chance = 1d;
	private int MinMobsPerSpawn = 1;
	private int MaxMobsPerSpawn = 1;
	
	private LinkedHashMap<MyHeroMobCreator,Integer> Mobs;
	private Region region; 
	private List<Vector3> spawnpoints;
	
	private List<Integer> SpawnOn;
	
	private boolean SpawninRooms = false;
	private boolean SpawninWater = false;
	
	private Level level;
	
	public Spawner()
	{
		SpawnOn = new ArrayList<Integer>();
		spawnpoints = new ArrayList<Vector3>();
		Mobs = new LinkedHashMap<>();
		SpawnedMobs = new ArrayList<>();
		SpawnOn.add(1);
		SpawnOn.add(2);
		SpawnOn.add(13);
		
	
	
	}
	
	public void addMob(MyHeroMobCreator mob, int chance)
	{
		Mobs.put(mob, chance);
	}
	
	public void Spawn()
	{
		int n = Mobs.size();
		List<Integer> weight = new ArrayList<Integer>();
		for(Map.Entry<MyHeroMobCreator,Integer> entry : Mobs.entrySet())
			weight.add(entry.getValue());

		double max_weight = Collections.max(weight);
		
		ArrayList<MyHeroMobCreator> tmplistmobs = (new ArrayList<MyHeroMobCreator>(Mobs.keySet()));
		Random r = new Random();
		int index;
		if(SpawnedMobs.size() < MaxMobs)
			for (int i = 0; i < spawnpoints.size(); i++) {
				while (true) {
					index = (int) (Math.random() * n);
					if (Math.random() < weight.get(index)/ max_weight) break;
				}
				
				tmplistmobs.get(index).SpawnEntity(spawnpoints.get(r.nextInt(spawnpoints.size())),level);
				SpawnedMobs.add(tmplistmobs.get(index).getEntity());
			}

	}
	
	public void generateSpawnPoints()
	{
		int xmin = (int) Math.min(region.getP1().x, region.getP2().x);
		int xmax = (int) Math.max(region.getP1().x, region.getP2().x);
		
		int ymin = (int) Math.min(region.getP1().y, region.getP2().y);
		int ymax = (int) Math.max(region.getP1().y, region.getP2().y);
		
		
		int denst = 16/Density;
		for(int x = xmin ; x < xmax;x+=denst)
		{
			for(int y = ymin ; y < ymax;y+=denst)
			{
				getPossitions(x,y,0);
			}
		}
		
	}
	
	public void getPossitions(int x,int z,int retry)
	{
		for(int y = 255;y > 0;y--)
		{
			if(SpawnOn.contains(level.getBlockIdAt(x, y, z)) && level.getBlockIdAt(x, y+1, z) == 0)
			{
				spawnpoints.add(new Vector3(x, y, z));
				if(SpawninRooms) continue;
				else return;
			}
		}
		if(retry != MaxRetry)
		{
			Random r = new Random();
			x +=  r.nextInt(16)-8;
			z += r.nextInt(16)-8;
			getPossitions(x,z,++retry);
		}
	}
	
	
	
}
