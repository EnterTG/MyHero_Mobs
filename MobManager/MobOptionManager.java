package MobManager;

import java.util.HashMap;

import MobOptions.MobOption_Damage;
import MobOptions.MobOption_HP;
import MobOptions.MobOption_Name;

interface OptionInterface
{
	MobOption Create(AbstractMobOption e);
}

public class MobOptionManager
{
	public static HashMap<String,OptionInterface> ListOptions = new HashMap<String,OptionInterface>();

	public static void Load()
	{
		ListOptions.put("Name", e -> new MobOption_Name(e));
		ListOptions.put("Health", e -> new MobOption_HP(e));
		ListOptions.put("Damage", e -> new MobOption_Damage(e));
	}
	
	
}
