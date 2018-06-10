package MobOptions;

import Core.LangManager;
import Core.MyHeroMain;
import MobManager.AbstractMobOption;
import MobManager.MobOption;
import de.kniffo80.mobplugin.entities.monster.Monster;

public class MobOption_Damage extends MobOption
{
	public MobOption_Damage(AbstractMobOption MobOption)
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
			MyHeroMain.Main.getLogger().info(LangManager.Mob_HP_Is_Not_Int);
		}
	}


	/*@Override
	public AbstractMobOption getMobOptionList() {
		// TODO Auto-generated method stub
		return null;
	}*/


	@Override
	public void executeMobOption() {
		if(getEntity() instanceof Monster )
			((Monster)getEntity()).setDamage(new float[] {val});
		MobOption.executeMobOption();
	}
}
