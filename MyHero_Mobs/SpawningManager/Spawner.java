package MyHero_Mobs.SpawningManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Mobs.MobManager.MyHeroMobCreator;
import MyHero_Mobs.RegionsManager.Region;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.math.Vector3;

public class Spawner {

	
	//Gethers and seters
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
	
	public int getMaxMobs() {
		return MaxMobs;
	}

	public void setMaxMobs(int maxMobs) {
		MaxMobs = Math.max(1, maxMobs);
	}

	public Region getRegion()
	{
		return region;
	}
	
	public void addMob(MyHeroMobCreator mob, int chance)
	{
		Mobs.put(mob, chance);
	}

	public int getTimer() {
		return Timer;
	}

	public void setTimer(int timer) {
		Timer = timer;
	}
	/*
	*
	*	
	*
	*/
	public void generateSpawnPoints()
	{
		region.generateSpawnPoints(spawnpoints);
	}
	
	public void MobDie(Entity mob)
	{
		if(SpawnedMobs.contains(mob)) SpawnedMobs.remove(mob);
	}
	
	
	/*
	 * 
	 * Variables
	 * 
	 */
	
	private int MaxMobs = 1;

	private List<Entity> SpawnedMobs;
	
	private double Chance = 1d;
	private int MinMobsPerSpawn = 1;
	private int MaxMobsPerSpawn = 1;
	private int Timer = 2;
	
	
	
	private LinkedHashMap<MyHeroMobCreator,Integer> Mobs;
	private Region region; 
	private List<Vector3> spawnpoints;
	
	private List<Integer> weight;
	private double max_weight;
	private ArrayList<MyHeroMobCreator> tmplistmobs;
	
	public Spawner()
	{
		spawnpoints = new ArrayList<Vector3>();
		Mobs = new LinkedHashMap<>();
		SpawnedMobs = new ArrayList<>();
	}
	/*
	 * 
	 * Functions
	 * 
	 * 
	 */
	
	private boolean Activated = true;
	public void Stop()
	{
		Activated = false;
	}
	
	
	public void Spawn()
	{
		if(!Activated) return;
		if(weight == null)
		{
			weight = new ArrayList<Integer>();
			for(Map.Entry<MyHeroMobCreator,Integer> entry : Mobs.entrySet())
				weight.add(entry.getValue());
	
			max_weight = Collections.max(weight);
			tmplistmobs = (new ArrayList<MyHeroMobCreator>(Mobs.keySet()));
		}

		if(checkSpawner())
		{
			Repeat();
			return;
		}
		
		Random r = new Random();
		
		int n = Mobs.size();
		int index;
		
		//LangManager.Log("List size: " +SpawnedMobs.size() + " MaxMobs: " + MaxMobs);
		//LangManager.Log("Mobs in spawner: " +SpawnedMobs.size());
		
		for (int i = 0; i < r.nextInt(MaxMobsPerSpawn)+MinMobsPerSpawn; i++) 
		{
			
			if(SpawnedMobs.size() < MaxMobs)
			{
				if(r.nextDouble() <= Chance)
				{
					while (true) {
						index = (int) (Math.random() * n);
						if (Math.random() < weight.get(index)/ max_weight) break;
					}
					//LangManager.Log("Spawning mob");
					SpawnedMobs.add(tmplistmobs.get(index).SpawnEntity(spawnpoints.get(r.nextInt(spawnpoints.size())),region.getWorld()));
					//SpawnedMobs.add(tmplistmobs.get(index).getEntity());
				}
			}
			else
			{
				break;
			}
		}
		
		Repeat();
	}
	
	private void Repeat()
	{
		MyHeroMain.getMain().getServer().getScheduler().scheduleDelayedTask(MyHeroMain.getMain(), 
				new Runnable() 
				{
					public void run() 
					{
						Spawn();
					}
				}
		, Timer*20,false);
	}
	
	private boolean checkSpawner()
	{
		boolean exit = true;
		for(Player p : region.getWorld().getPlayers().values())
		{
			if(region.playerInRegion(p)) {
				exit =  false;
				break;
			}
		}
		
		return exit;
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
