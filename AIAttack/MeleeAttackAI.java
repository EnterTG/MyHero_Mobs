package AIAttack;


import MobManager.MyHeroMob;
import StateMachine.State;
import StateMachine.StateMachineCore;
import StateMachine.Triggers;
import cn.nukkit.Server;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.network.protocol.EntityEventPacket;

public class MeleeAttackAI implements State
{
	private MyHeroMob Entity;
	private StateMachineCore Machine;

	
	public MeleeAttackAI(MyHeroMob entity,StateMachineCore machinecore)
	{
		Machine = machinecore;
		Entity = entity;
	}

	public void onAttack()
	{
		/*EntityDamageByEntityEvent DamageEvent = new EntityDamageByEntityEvent(Entity,Entity.getTarget(), EntityDamageEvent.DamageCause.ENTITY_ATTACK,Entity.getDamage());
		MyHeroMain.Main.getServer().getPluginManager().callEvent(DamageEvent);*/
		Entity._AtackDelay = 0;
		Entity.getTarget()
		.attack(new EntityDamageByEntityEvent
				(Entity,Entity.getTarget(), EntityDamageEvent.DamageCause.ENTITY_ATTACK,Entity.getDamage()));
		EntityEventPacket pk = new EntityEventPacket();
		pk.eid = Entity.getTarget().getId();
		pk.event = 2;
		Server.broadcastPacket(Entity.getTarget().getViewers().values(), pk);
		
	}
	
	
	
	

	public boolean canAttack()
	{
		if(Entity._AtackDelay > 10 && Entity.getTarget() != null)
			if(Entity.getLocation().distance(Entity.getTarget().getLocation()) <= 1.25)
				return true;
			
			else
				return false;
		else
			return false;
		
	}


	@Override
	public void Execute() {
		if(canAttack())
			onAttack();
		else
			Machine.Fire(Triggers.TargetNotInRange);
		
		
	}
	
}
