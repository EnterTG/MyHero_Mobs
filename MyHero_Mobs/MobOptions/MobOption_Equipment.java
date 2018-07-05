package MyHero_Mobs.MobOptions;

import java.util.List;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Mobs.MobManager.AbstractMobOption;
import MyHero_Mobs.MobManager.MobOption;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.MobArmorEquipmentPacket;
import cn.nukkit.network.protocol.MobEquipmentPacket;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.scheduler.Task;

public class MobOption_Equipment extends MobOption{

	public MobOption_Equipment(AbstractMobOption MobOption) {
		super(MobOption);
		// TODO Auto-generated constructor stub
	}
	
	private Item[] eq = new Item[] {Item.get(0),Item.get(0),Item.get(0),Item.get(0),Item.get(0),Item.get(0)};
	
	@Override
	public void executeMobOption() {
		ServerScheduler scheduler = MyHeroMain.getMain().getServer().getScheduler();
		/*MobEquipmentPacket pk = new MobEquipmentPacket();
		
		pk.eid = getEntity().getId();
		pk.item = new ItemBow();
		pk.hotbarSlot = 0;
		pk.windowId = -1;
		this.getEntity().getLevel().getPlayers().forEach( (in,p) -> 
		{
			scheduler.scheduleDelayedTask(new Task() {
				@Override
				public void onRun(int arg0) {
					p.sendMessage("Test");
					p.dataPacket(pk);
				}
			}, 20);
			
			
			//MyHeroMain.getMain().getServer().getPlayer("WindSkullX").dataPacket(pk);
		} 
		);*/
		//LangManager.Log(this.getEntity().getId()+"");
		for(int i = 0 ; i < 3;i++)
		{	
			if(i <= 1)
			{
				if(eq[i] != null)
				{
					MobEquipmentPacket pk = new MobEquipmentPacket();
					
					pk.eid = this.getEntity().getId();
					pk.item = eq[i];
					pk.hotbarSlot = i;
					this.getEntity().getLevel().getPlayers().forEach( (in,p) -> 
					{
						scheduler.scheduleDelayedTask(new Task() {
							@Override
							public void onRun(int arg0) {
								p.dataPacket(pk);
							}
						}, 1);
					} 
					);
				}
			}
			else
			{
				MobArmorEquipmentPacket pk = new MobArmorEquipmentPacket();
				pk.eid = this.getEntity().getId();
				Item[] armor = new Item[4];
				System.arraycopy(eq, 2, armor, 0, 4);
				pk.slots = armor;
				this.getEntity().getLevel().getPlayers().forEach( (in,p) -> 
					{
						scheduler.scheduleDelayedTask(new Task() {
							@Override
							public void onRun(int arg0) {
								p.dataPacket(pk);
							}
						}, 1);
					} 
				);
			}
			
		}
		MobOption.executeMobOption();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addMobOption(Object... value) {
		for(String s : ((List<String>)value[0]))
		{
			String[] item = s.split(":");			
			
			if(MyHeroMain.getMyHeroData().getDataItems().itemExist(item[0]))
			{
				eq[Integer.parseInt(item[1])] = MyHeroMain.getMyHeroData().getDataItems().getItem(item[0]).spawnItem();
			}
		}
		setOption(this);
	}
	
}
