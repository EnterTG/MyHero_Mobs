package MobManager;

import AIAttack.MeleeAttackAI;
import AIMove.MobWalkingAI;
import Core.MyHeroMain;
import MobAIInterface.Attack;
import MobAIInterface.Move;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityLiving;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class MyHeroMob extends EntityLiving
{
	private int Network_ID = 0;
	public MyHeroMob(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}
	
	private Entity Target = null;
	private int Damage = 0;
	
	private Attack AttackBehaviour = new MeleeAttackAI(this);
	private Move MoveBehaviour = new MobWalkingAI(this);
	
	

	public void BaseTick()
	{
		AttackBehaviour.Tick();
		if(AttackBehaviour.canAttack()) AttackBehaviour.onAttack();
		else MoveBehaviour.Move();
		//return super.entityBaseTick();
		MyHeroMain.Main.getServer().getScheduler().scheduleDelayedTask(() -> BaseTick()
		,1);
		
		
	}
	
	@Override
	public int getNetworkId()
	{
		return Network_ID;
	}

	public Entity getTarget()
	{
		return Target;
	}
	public void setTarget(Entity target)
	{
		Target = target;
	}
	public int getDamage()
	{
		return Damage;
	}
	
	
	
	
	
	
	
}
