package MobManager;

import cn.nukkit.entity.Entity;

public interface AbstractMobOption
{
	
	//Entity Entity = null;
	
	void addMobOption(String... value);
	
	Entity getEntity();
}
