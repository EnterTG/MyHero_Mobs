package MyHero_Mobs.SpawningManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MyHero_Mobs.MobManager.MyHeroMobCreator;
import MyHero_Mobs.RegionsManager.Region;
import cn.nukkit.entity.Entity;
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



	public void setRegion(Region region) {
		this.region = region;
	}




	private static final int MaxMobs = 10;

	private List<Entity> SpawnedMobs;
	
	private double Chance = 1d;
	private int MinMobsPerSpawn = 1;
	private int MaxMobsPerSpawn = 1;
	
	private LinkedHashMap<MyHeroMobCreator,Integer> Mobs;
	private Region region; 
	private List<Vector3> spawnpoints;
	

	

	

	
	public Spawner()
	{
		
		spawnpoints = new ArrayList<Vector3>();
		Mobs = new LinkedHashMap<>();
		SpawnedMobs = new ArrayList<>();
		/*SpawnOn.add(1);
		SpawnOn.add(2);
		SpawnOn.add(13);*/
		
	
	
	}
	public Region getRegion()
	{
		return region;
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
				
				tmplistmobs.get(index).SpawnEntity(spawnpoints.get(r.nextInt(spawnpoints.size())),region.getWorld());
				SpawnedMobs.add(tmplistmobs.get(index).getEntity());
			}

	}
	
	public void generateSpawnPoints()
	{
		region.generateSpawnPoints(spawnpoints);
		
	}
	
	
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Region:" + region.toString() +System.lineSeparator()+" Mobs: " +System.lineSeparator());
		Mobs.keySet().stream().forEach( (s) -> {builder.append(s.toString());});
		return builder.toString();
		
	}
	
	
}
