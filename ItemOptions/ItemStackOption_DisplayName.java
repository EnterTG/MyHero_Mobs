package ItemOptions;

import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;
import cn.nukkit.utils.TextFormat;

public class ItemStackOption_DisplayName extends ItemStackOption
{
	public ItemStackOption_DisplayName(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}

	@Override
	public void addItemStackOption(Object... value)
	{
		getItem().setCustomName(TextFormat.colorize(value[0].toString()));
	}
	
}
