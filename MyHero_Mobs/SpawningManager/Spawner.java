package MyHero_Mobs.SpawningManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MyHero_Core.Managers.LangManager;
import MyHero_Mobs.MobManager.MyHeroMobCreator;
import MyHero_Mobs.RegionsManager.Region;
import cn.nukkit.entity.Entity;
import cn.nukkit.math.Vector3;

public class Spawner {

	public double getChance() {
		return Chance;
	}

	public void setChance(double chance) {
		Chance =  Math.max(1, chance);
	}

	public int getMinMobsPerSpawn() {
		return MinMobsPerSpawn;
	}

	public void setMinMobsPerSpawn(int minMobsPerSpawn) {
		MinMobsPerSpawn = Math.max(1, minMobsPerSpawn);
	}

	public int getMaxMobsPerSpawn() {
		return MaxMobsPerSpawn;
	}

	public void setMaxMobsPerSpawn(int maxMobsPerSpawn) {
		MaxMobsPerSpawn =  Math.max(1, maxMobsPerSpawn);
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




	private int MaxMobs = 1;

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
		//LangManager.Log(toString());
		int n = Mobs.size();
		List<Integer> weight = new ArrayList<Integer>();
		for(Map.Entry<MyHeroMobCreator,Integer> entry : Mobs.entrySet())
			weight.add(entry.getValue());

		double max_weight = Collections.max(weight);
		
		ArrayList<MyHeroMobCreator> tmplistmobs = (new ArrayList<MyHeroMobCreator>(Mobs.keySet()));
		Random r = new Random();
		int index;
		//LangManager.Log("List size: " +SpawnedMobs.size() + " MaxMobs: " + MaxMobs);
		
		for (int i = 0; i < spawnpoints.size(); i++) 
		{
			if(SpawnedMobs.size() < MaxMobs)
			{
				if(r.nextDouble() <= Chance)
				{
					while (true) {
						index = (int) (Math.random() * n);
						if (Math.random() < weight.get(index)/ max_weight) break;
					}
					LangManager.Log("Spawning mob");
					tmplistmobs.get(index).SpawnEntity(spawnpoints.get(r.nextInt(spawnpoints.size())),region.getWorld());
					SpawnedMobs.add(tmplistmobs.get(index).getEntity());
				}
			}
			else
			{
				break;
			}
		}
	}
	
	public void generateSpawnPoints()
	{
		region.generateSpawnPoints(spawnpoints);
		LangManager.Log("Amount spawn points: "  + spawnpoints.size());
	}
	
	
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Region:" + region.toString() +System.lineSeparator()+" Mobs: " +System.lineSeparator());
		Mobs.keySet().stream().forEach( (s) -> {builder.append(s.toString());});
		return builder.toString();
		
	}

	public int getMaxMobs() {
		return MaxMobs;
	}

	public void setMaxMobs(int maxMobs) {
		LangManager.Log("Set max mobs to: " + maxMobs );
		MaxMobs = Math.max(1, maxMobs);
	}
	
	
}
