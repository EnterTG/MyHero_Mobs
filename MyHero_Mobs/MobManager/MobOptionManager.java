package MyHero_Mobs.MobManager;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataMobs;
import MyHero_Mobs.MobOptions.MobOption_Damage;
import MyHero_Mobs.MobOptions.MobOption_HP;
import MyHero_Mobs.MobOptions.MobOption_Name;


public class MobOptionManager
{
	

	public static void Load()
	{
		DataMobs datamobs = MyHeroMain.getMyHeroData().getDataMobs();
		datamobs.addMobOption("Name", e -> new MobOption_Name(e));
		datamobs.addMobOption("Health", e -> new MobOption_HP(e));
		datamobs.addMobOption("Damage", e -> new MobOption_Damage(e));
	}
	
	
}
