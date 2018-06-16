package Core;

import DropManager.DropManager;
import MobManager.MobManager;
import MobManager.MobOptionManager;
import SpawningMamager.Spawner;
import SpawningMamager.SpawningManager;



public class LoaderManager
{
	public static void LoadAll()
	{
		/*ItemStackOptionManager.Load();
		ItemStackManager.LoadItems();*/
		
		DropManager.Load();
		
		MobOptionManager.Load();
		MobManager.Load();
		
		SpawningManager.Load();
		
		
		MyHeroMain.Main.getServer().getScheduler().scheduleRepeatingTask(MyHeroMain.Main, 
				new Runnable() 
				{
					public void run() 
					{
						MyHeroMain.Main.getLogger().info("SpawningMobs");
						for(Spawner s : SpawningManager.AllSpawners)
						{
							s.Spawn();
						}
					}
				}
		, 100,true);
		//ObjectMapper mapper = new ObjectMapper();
		
		//For testing
		//MyHeroMobCreator mobo = new MyHeroMobCreator(43);
		
		//Entity mobo = Entity.createEntity(43,new Position(0,0,0,b.getServer().getDefaultLevel()));
		/*try
		{
			//Convert object to JSON string and save into file directly
			mapper.writeValue(new File("test.json"), AbstractMobOption.class);
			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(mobo);
			System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mobo);
			System.out.println(jsonInString);
			
			
		}
		catch ( JsonGenerationException e )
		{
			e.printStackTrace();
		}
		catch ( JsonMappingException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}*/
	}
	
	public static void Reload()
	{

		DropManager.AllDrops.clear();
		MobOptionManager.ListOptions.clear();
		MobManager.Mobs.clear();
		MobManager.MobsInterface.clear();
		LoadAll();
	}
}
