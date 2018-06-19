package Core;

import java.util.Map;

import Events.EntityDeathListener;
import MobManager.MobManager;
import MobManager.MobOption;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryOpenEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;

public class MyHeroMain extends PluginBase implements Listener
{
	public static MyHeroMain Main;
	
	private String NamePlugin = "MyHero Plugin for Nukkit";
	private double Version = 0.1;
	
	public MobManager mobmanager;
	

	
	@Override
	public void onEnable()
	{
		this.getLogger().info(NamePlugin + " Version: " + Version );
 
		this.getServer().getPluginManager().registerEvents(this,this);
		this.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		//Load Libs
		/*try {
			final File[] libs = new File[] {
					new File(getDataFolder(), "jackson-core-asl-1.9.9.jar"),
					new File(getDataFolder(), "jackson-mapper-asl-1.9.9.jar") };
			for (final File lib : libs) {
				if (!lib.exists()) {
					JarUtils.extractFromJar(lib.getName(),
							lib.getAbsolutePath());
				}
			}
			for (final File lib : libs) {
				if (!lib.exists()) {
					getLogger().warning("There was a critical error loading My plugin! Could not find lib: " + lib.getName());
					this.getServer().getPluginManager().disablePlugin(this);
					return;
				}
				addClassPath(JarUtils.getJarUrl(lib));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}*/
		MyHeroMain.Main = this;
		//mobmanager = new MobManager();
		LoaderManager.LoadAll();
		
		//mobmanager.runTaskTimerAsynchronously(Main, delay, period).runTaskAsynchronously(Main);
		//AsyncWorker MobsCalculations = new AsyncWorker();
		//this.getServer().getScheduler().scheduleTask(mobmanager);
		
		//Commands
		//PluginCommand main = (PluginCommand ) this.getServer().getPluginCommand("MyHeroMobs");
		//main.setExecutor(new Commands());
		
		/*ObjectMapper mapper = new ObjectMapper();
		
		//For testing
		MyHeroMobCreator user = new MyHeroMobCreator(5);
		MobOption mobo = new MobOption_Name(user);
		try {
			//Convert object to JSON string and save into file directly
			mapper.writeValue(new File("test.json"), AbstractMobOption.class);
			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(mobo);
			System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mobo);
			System.out.println(jsonInString);
			
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length > 0)
			switch (args[0]) {
				/*case "items":
					if(args.length > 1)
					{
						String ItemName = args[1];
						if( ItemStackManager.ItemList.containsKey(ItemName))
							if(sender instanceof Player )
								((Player)sender).getInventory().addItem(ItemStackManager.ItemList.get(ItemName).getItem());
							else
								sender.sendMessage(LangManager.Prefix);
					}
					break;*/
				case "mobs":
					MobManager.Mobs.get("Test1").getRoot().SpawnEntity(((Player)sender).getLocation());
					MyHeroMain.Main.getLogger().info("Mob stworzony");
					
					break;
				case "pokaz":
					for(Map.Entry<String, MobOption > Mob : MobManager.Mobs.entrySet())
					{
						this.getLogger().info("Mob == Key: " + Mob.getKey() + " Value: " + Mob.getValue().getEntity().toString());
					}
					
					break;
				case "reload":
					LoaderManager.Reload();
					break;
			}
		return true;
	}
	
	@Override
	public void onDisable()
	{
	
	
	}
	@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent e)
	{
		//MobManager.Mobs.get("test").getSource().SpawnEntity(e.getPlayer().level,e.getPlayer().getLocation());
		this.getLogger().info("Hey zalogowalem sie");
	
	}
	@EventHandler
	public void OnPlayerOpenInventory(InventoryOpenEvent e)
	{
		//this.getLogger().info("Hey otworzylem inventorys");
		
	}
	
}
