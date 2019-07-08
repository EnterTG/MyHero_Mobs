package MyHero_Mobs.MobOptions;

import MyHero_Core.Managers.LangManager;
import MyHero_Mobs.MobManager.AbstractMobOption;
import MyHero_Mobs.MobManager.MobOption;
import nukkitcoders.mobplugin.entities.monster.Monster;

public class MobOption_Damage extends MobOption
{
	public MobOption_Damage(AbstractMobOption MobOption)
	{
		super(MobOption);
	}
	
	private float val;
	public void addMobOption(Object... value)
	{
		//MyHeroMain.Main.getLogger().info("Val: " + getEntity());
		//getEntity().setNameTag(value[0]);
		try
		{
			val = Integer.parseInt(value[0]+"");
			setOption(this);
		}
		catch(Exception ex)
		{
			LangManager.Log(LangManager.Mob_Damage_Is_Not_Int);
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
			((Monster)getEntity()).setDamage(new float[] {val,val,val,val});
		MobOption.executeMobOption();
	}
}
