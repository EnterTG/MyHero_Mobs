package Core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Preconditions;

import DropManager.DropManager;
import MobManager.MobManager;
import MobManager.MobOptionManager;
import SpawningMamager.Spawner;
import SpawningMamager.SpawningManager;
import cn.nukkit.Server;
import cn.nukkit.utils.Utils;



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
	
	public static boolean saveResource(String filename, String outputName, boolean replace,String dir) {
        Preconditions.checkArgument(filename != null && outputName != null, "Filename can not be null!");
        Preconditions.checkArgument(filename.trim().length() != 0 && outputName.trim().length() != 0, "Filename can not be empty!");

        File out = new File(MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "")+ dir, outputName);
        if (!out.exists() || replace) {
            try (InputStream resource = MyHeroMain_Items.Main.getResource(filename)) {
                if (resource != null) {
                    File outFolder = out.getParentFile();
                    if (!outFolder.exists()) {
                        outFolder.mkdirs();
                    }
                    Utils.writeFile(out, resource);

                    return true;
                }
            } catch (IOException e) {
                Server.getInstance().getLogger().logException(e);
            }
        }
        return false;
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
