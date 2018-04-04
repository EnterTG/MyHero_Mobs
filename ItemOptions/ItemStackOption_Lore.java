package ItemOptions;

import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ItemStackOption_Lore extends ItemStackOption
{
	public ItemStackOption_Lore(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
		List<String> Lore =(List<String> ) value[0];
		for ( ListIterator i = Lore.listIterator(); i.hasNext(); )
		{
			i.set(TextFormat.colorize((String)i.next()));
		}
		getItem().setLore(Lore.toArray(new String[0]));
		//getItem().setLore(value[0].trim().substring(1,value[0].length()-1).split(", "));
	}
}
