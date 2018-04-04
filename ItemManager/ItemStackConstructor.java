package ItemManager;

import cn.nukkit.item.Item;

import java.util.Arrays;
import java.util.List;

public class ItemStackConstructor implements AbstractItemStackOption
{
	private Item Item;
	public ItemStackConstructor(int Id)
	{
		this.Item = Item.get(Id);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
	
	}
	@Override
	public Item getItem()
	{
		return this.Item;
	}
	
}
