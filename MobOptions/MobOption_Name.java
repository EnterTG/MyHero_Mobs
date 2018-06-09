package MobOptions;

import MobManager.AbstractMobOption;
import MobManager.MobOption;

public class MobOption_Name extends MobOption
{
	public MobOption_Name(AbstractMobOption MobOption)
	{
		super(MobOption);
	}
	
	private String val;
	public void addMobOption(String... value)
	{
		//MyHeroMain.Main.getLogger().info("Val: " + getEntity());
		//getEntity().setNameTag(value[0]);
		val = value[0];
		setOption(this);
	}


	/*@Override
	public AbstractMobOption getMobOptionList() {
		// TODO Auto-generated method stub
		return null;
	}*/


	@Override
	public void executeMobOption() {
		// TODO Auto-generated method stub
		getEntity().setNameTag(val);
		MobOption.executeMobOption();
	}
}
