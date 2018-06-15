package Core;

import cn.nukkit.utils.TextFormat;

public class LangManager
{
	public static String Prefix = TextFormat.GOLD+"["+TextFormat.GRAY+"MyHero"+TextFormat.GOLD+"] ",
			Mob_Dont_Have_Type = "%Mob_Name% do not have Type",
			Mob_Type_Is_Not_Int = "MobType is not a number in %Mob_Name% ",
			Mob_Load_Succes = "%Mob_Name% loadind ended with succes",
			Item_No_ID = "%Item_Name% do not have ID",
			Item_ID_Is_Not_Int = "%Item_Name% ID is not number",
			Item_Error= "%Item_Name% contains error",
			Item_Amount_Is_Not_Int = "Item amount is not number",
			Mob_HP_Is_Not_Int = "Mob hp is not number",
			Item_Do_Not_Exist = "%Item_Name% not exist",
			Drop_Error= "Error in drop %Drop_Name%",
			Numerc_Error_Spawner = "In %sSpawner_Name% number error",
			Spawner_Not_Loaded = "Spawner %Spawner_Name% not loaded";
	
	
	public enum LangHelper
	{
		ItemName("%Item_Name%"),MobName("%Mob_Name%"),DropName("%Drop_Name%"),SpawnerName("%Spawner_Name%");
		private String s;
		
		LangHelper(String a)
		{
			s = a;
		}
		
		@Override
		public String toString()
		{
			return s;
		}
	}
	
}
