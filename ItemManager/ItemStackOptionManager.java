package ItemManager;

import java.util.HashMap;

import ItemOptions.ItemStackOption_Amount;
import ItemOptions.ItemStackOption_DisplayName;
import ItemOptions.ItemStackOption_Enchant;
import ItemOptions.ItemStackOption_Lore;

interface ItemStackOptionInterface
{
	ItemStackOption Create(AbstractItemStackOption e);
}

public class ItemStackOptionManager
{
	public static HashMap<String,ItemStackOptionInterface> ListOptions = new HashMap<>();
	
	public static void Load()
	{
		ListOptions.put("DisplayName", e -> new ItemStackOption_DisplayName(e));
		ListOptions.put("Lore", e -> new ItemStackOption_Lore(e));
		ListOptions.put("Amount", e -> new ItemStackOption_Amount(e));
		ListOptions.put("Enchant", e -> new ItemStackOption_Enchant(e));
	}
	
}
