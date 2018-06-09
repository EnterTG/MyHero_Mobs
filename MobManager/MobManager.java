package MobManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.yaml.snakeyaml.Yaml;

import Core.LangManager;
import Core.MyHeroMain;
import cn.nukkit.scheduler.NukkitRunnable;

public class MobManager extends NukkitRunnable 
{
	public static HashMap<String,MobOption> Mobs = new HashMap<>();
	
	private List<MyHeroMob> MobsList = new ArrayList<>();
	private Queue<MyHeroMob> Queue = new PriorityQueue<>();
	
	public void addMob(MyHeroMob mob)
	{
		Queue.offer(mob);
	}
	private void MobTick()
	{
		long Start =  System.currentTimeMillis()/1000;

		MyHeroMob mymob;
		while((mymob = Queue.poll()) != null)
		{
			MobsList.add(mymob);
		}
		for(MyHeroMob mob : MobsList)
		{
			if(!mob.isAlive())
			{
				MobsList.remove(mob);
				mob.despawnFromAll();
			}
			else
				mob.Execute();
		}
		MyHeroMain.Main.getLogger().info("Wykonano w: " + (System.currentTimeMillis()/1000 - Start));
	}
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		File MobFileRoot = new File(MyHeroMain.Main.getDataFolder() + "/Mobs/");
		File[] MobsFileList = MobFileRoot.listFiles();
		//MyHeroMain.Main.getLogger().info(MobFileRoot.getPath());
		if(MobsFileList != null)
			for(File MobsFile : MobsFileList)
			{
				//MyHeroMain.Main.getLogger().info(MobsFile.getPath());
				if(MobsFile.isFile())
				{
					if(MobsFile.getName().contains(".yml"))
					{
						
						Yaml MobsFileYML = new Yaml();
						MobsFileYML.load(MobsFile.getPath());
						try
						{
							Map<String, Map<String, Object>> Mobs = MobsFileYML.loadAs(Files.newInputStream(Paths.get(MobsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Mob : Mobs.entrySet())
							{
								//MyHeroMain.Main.getLogger().info("Key: " + Mob.getKey());
								if(Mob.getValue()  != null &&Mob.getValue().containsKey("Type"))
								{
									int MobID = 0;
									try
									{
										MobID = Integer.parseInt(Mob.getValue().get("Type").toString());
									}
									catch(NumberFormatException nfe)
									{
										MyHeroMain.Main.getLogger().info(LangManager.Mob_Type_Is_Not_Int.replaceAll("%Mob_Name%",Mob.getKey()));
									}
										MyHeroMobCreator mobCreator = new MyHeroMobCreator(MobID);
										AbstractMobOption OptionLast = mobCreator;
										//MyHeroMain.Main.getLogger().info("Mob id: " + MobID);
										for(Map.Entry<String, Object> Options : Mob.getValue().entrySet())
										{
											if(MobOptionManager.ListOptions.containsKey(Options.getKey()))
											{
												//MyHeroMain.Main.getLogger().info("Option add: " + Options.getKey());
												//MyHeroMain.Main.getLogger().info("Option value: " + Options.getValue().toString());
												MobOption Option = MobOptionManager.ListOptions.get(Options.getKey().toString()).Create(OptionLast);
												Option.addMobOption(Options.getValue().toString());
												OptionLast = Option;
											}
										}
										MyHeroMain.Main.getLogger().info(LangManager.Mob_Load_Succes.replaceAll("%Mob_Name%",Mob.getKey()) );
										MobManager.Mobs.put(Mob.getKey(),(MobOption)OptionLast);
								}
								else
								{
									MyHeroMain.Main.getLogger().info(LangManager.Mob_Dont_Have_Type.replaceAll("%Mob_Name%",Mob.getKey()));
								}
							}
						}
						catch ( IOException e )
						
						{
							e.printStackTrace();
						}
						
					}
				}
			}
		/*MobOption mobo = new MobOption_Name(new MyHeroMobCreator(43));
		mobo.addMobOption("test");
		Mobs.put("test",mobo);*/
		
	}
	@Override
	public void run() {
		MobTick();
		//MyHeroMain.Main.mobmanager.runTaskAsynchronously(MyHeroMain.Main);
	}
}
