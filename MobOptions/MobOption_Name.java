package MobOptions;

import Core.MyHeroMain;
import MobManager.AbstractMobOption;
import MobManager.MobOption;
import cn.nukkit.entity.Entity;

public class MobOption_Name extends MobOption
{
	public MobOption_Name(AbstractMobOption MobOption)
	{
		super(MobOption);
	}
	

	public void addMobOption(String... value)
	{
		//MyHeroMain.Main.getLogger().info("Val: " + getEntity());
		getEntity().setNameTag(value[0]);
		
	}
}
