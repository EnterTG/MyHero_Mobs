package MyHero_Mobs.DropManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.nukkit.item.Item;

public class Drop {

	public int getMinDrop() {
		return MinDrop;
	}
	public void setMinDrop(int minDrop) {
		MinDrop =Math.max(1, minDrop);
	}
	public int getMaxDrop() {
		return MaxDrop;
	}
	public void setMaxDrop(int maxDrop) {
		MaxDrop = Math.max(1, maxDrop);
	}


	private int MinDrop = 1, MaxDrop = 1;
	private Double DropChance = 1d;
	
	private List<ChanceItem> Drops = new ArrayList<ChanceItem>();
	
	public Drop()
	{
		
	}
	public void addItem(ChanceItem item)
	{
		Drops.add(item);
	}
	
	
	public Item[] getDrop()
	{
		Random r = new Random();
		if(r.nextDouble() <= DropChance)
		{
			List<Item> itemsdrop = new ArrayList<>();
			int n = Drops.size();
			List<Double> weight = new ArrayList<Double>();
			
			for(int i = 0; i < n;i++)
				weight.add(Drops.get(i).getChance());
			
			double max_weight = Collections.max(weight);
			
			int n_select =  r.nextInt(MaxDrop)+MinDrop;
			
			int index;
			for (int i = 0; i < n_select; i++) {
				while (true) {
					index = (int) (Math.random() * n);
					if (Math.random() < weight.get(index)/ max_weight) break;
				}
				
				Item[] itemdrop = Drops.get(index).getItem();
				for(int x = 0 ; x < itemdrop.length;x++)
					itemsdrop.add(itemdrop[x]);
			}
			
			return (Item[]) itemsdrop.toArray(new Item[itemsdrop.size()]);
		}
		return new Item[0];
	}
	public Double getDropChance() {
		return DropChance;
	}
	public void setDropChance(Double dropChance) {
		DropChance = Math.max(1, dropChance);
	}
	
}
