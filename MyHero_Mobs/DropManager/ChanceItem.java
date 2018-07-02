package MyHero_Mobs.DropManager;

import java.util.Random;

import MyHero_Items.ItemManager.ItemStackConstructor;
import cn.nukkit.item.Item;

public class ChanceItem {

	
	public int getAmountmin() {
		return Amountmin;
	}
	public void setAmountmin(int amountmin) {
		Amountmin = amountmin;
	}
	public int getAmountmax() {
		return Amountmax;
	}
	public void setAmountmax(int amountmax) {
		Amountmax = amountmax;
	}

	private int Amountmin = 1, Amountmax = 1;
	
	public Item[] getItem() {
		int amount = new Random().nextInt(Amountmax)+Amountmin;
		Item[] drop = new Item[amount];
		for(int i =0; i < amount;i++)
			drop[i] = item.spawnItem();
		return drop;
	}
	public void setItem(ItemStackConstructor item) {
		this.item = item;
	}
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
	
	private ItemStackConstructor item;
	private double chance;
	
	public ChanceItem(ItemStackConstructor i,double d)
	{
		item = i;
		chance = d;
	}
}
