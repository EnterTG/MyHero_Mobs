package MyHero_Mobs.Core;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Mobs.Events.EntityDeathListener;
import cn.nukkit.plugin.PluginBase;

public class MyHeroMain_Mobs extends PluginBase
{

	

	
	@Override
	public void onEnable()
	{
		this.getServer().getPluginManager().registerEvents(new EntityDeathListener(),this);
		MyHeroMain.getMyHeroData().InicializeDataMobs();
		MyHeroMain.getMyHeroData().getDataMobs().Start();
		LoaderManager.LoadAll();
	}
	
	

	
	@Override
	public void onDisable()
	{
	
	
	}

	
}
