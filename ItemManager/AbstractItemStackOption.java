package ItemManager;

import cn.nukkit.item.Item;

public interface AbstractItemStackOption
{
	
	void addItemStackOption(Object... value);
	Item getItem();
}
