package MyHero_Mobs.MobOptions;

import MyHero_Core.Managers.LangManager;
import MyHero_Mobs.MobManager.AbstractMobOption;
import MyHero_Mobs.MobManager.MobOption;

public class MobOption_HP extends MobOption
{
	public MobOption_HP(AbstractMobOption MobOption)
	{
		super(MobOption);
	}
	
	private int val;
	public void addMobOption(String... value)
	{
		//MyHeroMain.Main.getLogger().info("Val: " + getEntity());
		//getEntity().setNameTag(value[0]);
		try
		{
			val = Integer.parseInt(value[0]);
			setOption(this);
		}
		catch(Exception ex)
		{
			LangManager.Log(LangManager.Mob_HP_Is_Not_Int);
		}
	}


	/*@Override
	public AbstractMobOption getMobOptionList() {
		// TODO Auto-generated method stub
		return null;
	}*/


	@Override
	public void executeMobOption() {
		// TODO Auto-generated method stub
		getEntity().setMaxHealth(val);
		MobOption.executeMobOption();
	}
}
