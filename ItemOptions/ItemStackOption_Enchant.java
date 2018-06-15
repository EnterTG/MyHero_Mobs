package ItemOptions;

import java.util.List;

import Core.MyHeroMain;
import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;
import cn.nukkit.item.enchantment.Enchantment;

public class ItemStackOption_Enchant extends ItemStackOption
{
	public ItemStackOption_Enchant(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addItemStackOption(Object... value)
	{
		try
		{
			List<String> Enchants = (List<String>)value[0];
			for(String enchant : Enchants)
			{
				try
				{
					String[] enchantparateters = enchant.split(":");
					int EnchantID = Integer.parseInt(enchantparateters[0]);
					int Power = Integer.parseInt(enchantparateters[1]);
					Enchantment e = Enchantment.getEnchantment(EnchantID);
					e.setLevel(Power, false);
					getItem().addEnchantment(e);
				}
				catch(NumberFormatException e)
				{
					MyHeroMain.Main.getLogger().info(e.toString());
				}
			}
		}
		catch(Exception ex)
		{
			MyHeroMain.Main.getLogger().info(ex.toString());
		}
		
	}
}
