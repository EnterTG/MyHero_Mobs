package MobManager;

import Core.LangManager;
import Core.MyHeroMain;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MobManager
{
	public static HashMap<String,MobOption> Mobs = new HashMap<>();
	
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
										//MyHeroMain.Main.getLogger().info(LangManager.Mob_Load_Succes.replaceAll("%Mob_Name%",Mob.getKey()) );
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
}
