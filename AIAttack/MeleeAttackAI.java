package AIAttack;

import MobAIInterface.Attack;
import MobManager.MyHeroMob;
import cn.nukkit.Server;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.network.protocol.EntityEventPacket;

public class MeleeAttackAI implements Attack
{
	private MyHeroMob Entity;
	private int AtackDelay = 0;
	public MeleeAttackAI(MyHeroMob MyHeroMob)
	{
		Entity = MyHeroMob;
	}
	
	@Override
	public void Tick()
	{
		AtackDelay += 1;
	}
	
	@Override
	public void onAttack()
	{
		/*EntityDamageByEntityEvent DamageEvent = new EntityDamageByEntityEvent(Entity,Entity.getTarget(), EntityDamageEvent.DamageCause.ENTITY_ATTACK,Entity.getDamage());
		MyHeroMain.Main.getServer().getPluginManager().callEvent(DamageEvent);*/
		AtackDelay = 0;
		Entity.getTarget().attack(new EntityDamageByEntityEvent(Entity,Entity.getTarget(), EntityDamageEvent.DamageCause.ENTITY_ATTACK,Entity.getDamage()));
		EntityEventPacket pk = new EntityEventPacket();
		pk.eid = Entity.getTarget().getId();
		pk.event = 2;
		Server.broadcastPacket(Entity.getTarget().getViewers().values(), pk);
		
	}
	
	
	
	
	@Override
	public boolean canAttack()
	{
		if(AtackDelay > 10 && Entity.getTarget() != null)
			if(Entity.getLocation().distance(Entity.getTarget().getLocation()) <= 1.25)
				return true;
			
			else
				return false;
		else
			return false;
	}
	
}
