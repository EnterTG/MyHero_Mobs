package ItemOptions;

import Core.LangManager;
import Core.MyHeroMain;
import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;

public class ItemStackOption_Amount extends ItemStackOption
{
	public ItemStackOption_Amount(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
		try
		{
			int Amount = Integer.parseInt(value[0].toString());
			getItem().setCount(Amount);
			
		}
		catch(NumberFormatException nfe)
		{
			MyHeroMain.Main.getLogger().info(LangManager.Item_Amount_Is_Not_Int);
		}
		
		
	}
}
