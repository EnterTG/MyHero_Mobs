package MyHero_Mobs.Core;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataMobs;



public class LoaderManager
{
	public static void LoadAll()
	{
		/*ItemStackOptionManager.Load();
		ItemStackManager.LoadItems();*/
		/*MyHeroMain_Mobs.Main.getLogger().info("-----Start load Drops ------");
		DropManager.Load();
		MyHeroMain_Mobs.Main.getLogger().info("-----Start load Mobs ------");
		MobOptionManager.Load();
		MobManager.Load();
		MyHeroMain_Mobs.Main.getLogger().info("-----Start load Spawners ------");
		SpawningManager.Load();
		
		*/
		MyHeroMain.getMain().getServer().getScheduler().scheduleRepeatingTask(MyHeroMain.getMain(), 
				new Runnable() 
				{
					public void run() 
					{
						try
						{
							DataMobs dataitems = MyHeroMain.getMyHeroData().getDataMobs();
							dataitems.getStreamSpawners().forEach( (s) -> { s.Spawn(); });
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
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
	
	
	
	
	/*public static void Reload()
	{

		DropManager.AllDrops.clear();
		MobOptionManager.ListOptions.clear();
		MobManager.Mobs.clear();
		MobManager.MobsInterface.clear();
		LoadAll();
	}*/
}
