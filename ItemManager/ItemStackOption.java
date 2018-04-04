package ItemManager;

import cn.nukkit.item.Item;

public abstract class ItemStackOption implements AbstractItemStackOption
{
	protected AbstractItemStackOption itemStackOption;
	public ItemStackOption(AbstractItemStackOption itemStackOption)
	{
		this.itemStackOption = itemStackOption;
	}
	
	public Item getItem()
	{
		return itemStackOption.getItem();
	}
	
	

}
