package MyHero_Mobs.MobManager;

import cn.nukkit.entity.Entity;

public interface AbstractMobOption
{
	
	//Entity Entity = null;
	AbstractMobOption getMobOptionList();
	void setOption(AbstractMobOption a);
	MyHeroMobCreator getRoot();
	void executeMobOption();
	void addMobOption(Object... value);

	Entity getEntity();
}
