package MobManager;

import  cn.nukkit.entity.Entity;

public abstract class MobOption implements AbstractMobOption
{
	protected AbstractMobOption MobOption;
	
	public MobOption (AbstractMobOption MobOption)
	{
		
		this.MobOption = MobOption;
	}
	
	public MyHeroMobCreator getSource()
	{
		if(MobOption instanceof MyHeroMobCreator ) return ( MyHeroMobCreator )MobOption;
		else return ((MobOption)MobOption).getSource();
	}
	public MyHeroMobCreator getRoot()
	{
		return MobOption.getRoot();
	}
	@Override
	public void setOption(AbstractMobOption a)
	{
		MobOption.setOption(a);
	}

	@Override
	public Entity getEntity()
	{
		return MobOption.getEntity();
	}
	@Override
	public AbstractMobOption getMobOptionList()
	{
		return MobOption.getMobOptionList();
	}
	
	
}
