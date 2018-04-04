package ItemOptions;

import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;

public class ItemStackOption_Enchant extends ItemStackOption
{
	public ItemStackOption_Enchant(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
		//getItem().addEnchantment();
	}
}
