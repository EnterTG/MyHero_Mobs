package MobManager;

import MobOptions.MobOption_Name;

import java.util.HashMap;

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

	}
	
	
}
