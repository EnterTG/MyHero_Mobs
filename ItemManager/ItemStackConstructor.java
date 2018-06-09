package ItemManager;

import cn.nukkit.item.Item;

public class ItemStackConstructor implements AbstractItemStackOption
{
	private Item i;
	public ItemStackConstructor(int Id)
	{
		this.i = Item.get(Id);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
	
	}
	@Override
	public Item getItem()
	{
		return this.i;
	}
	
}
