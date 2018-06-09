package MobManager;

import AIAttack.MeleeAttackAI;
import AIMove.MobWalkingAI;
import AITargetSelector.MobTargetSelector;
import MobPhysis.MyHeroMobPhysis;
import MobPhysis.MyHeroMobPhysisFunctions;
import StateMachine.IdleState;
import StateMachine.State;
import StateMachine.StateMachineConfigure;
import StateMachine.StateMachineCore;
import StateMachine.States;
import StateMachine.Triggers;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityLiving;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddEntityPacket;

public class MyHeroMob extends EntityLiving implements State
{
	
	private StateMachineCore StateMachine;
	private int Network_ID = 43;
	
	private MyHeroMobPhysis Physis;
	
	public MyHeroMob(FullChunk chunk, CompoundTag nbt) {
		
		super(chunk, nbt);
		
		Physis = MyHeroMobPhysisFunctions.getWalkingPhysis();
		
		
		
		//State machine configuration
		StateMachine = new StateMachineCore(this);
		
		StateMachine.addState(new MobTargetSelector(this,StateMachine), States.Search);
		StateMachine.addState(new MobWalkingAI(this,StateMachine),      States.Travel);
		StateMachine.addState(new MeleeAttackAI(this,StateMachine),     States.Combat);
		StateMachine.addState(new IdleState(StateMachine),		      States.Idle);
		
		StateMachineConfigure Configure = StateMachine.getConfigure();
		Configure.Configure(States.Combat)
		.Add(Triggers.TargetDead,       States.Search)
		.Add(Triggers.TargetEscape,     States.Search)
		.Add(Triggers.TargetNotInRange, States.Travel)
		.Add(Triggers.TargerLost,       States.Search)
		.Add(Triggers.Error,		    States.Error)
		.Add(Triggers.Wait,		     States.Idle);
		
		Configure.Configure(States.Evade)
		.Add(Triggers.TargerLost, States.Search)
		.Add(Triggers.Error, States.Error);
		
		Configure.Configure(States.Search)
		.Add(Triggers.TargetFound,      States.Travel)
		.Add(Triggers.TargetFoundEvade, States.Evade)
		.Add(Triggers.Error,		    States.Error)
		.Add(Triggers.Wait,		        States.Idle);
		
		Configure.Configure(States.Idle)
		.Add(Triggers.EndWait, States.Search)
		.Add(Triggers.Error,   States.Error);
		
		Configure.Configure(States.Travel)
		.Add(Triggers.TargetInRange, States.Combat)
		.Add(Triggers.TargerLost,    States.Search)
		.Add(Triggers.Wait,		     States.Idle)
		.Add(Triggers.Error	 ,       States.Error);
		
		
		
		StateMachine.Log();
		
		
	}
	
	private Entity Target = null;
	private final int _Damage = 0;
	public int _AtackDelay = 0;
	
	/*private Attack AttackBehaviour = new MeleeAttackAI(this);
	private Move MoveBehaviour = new MobWalkingAI(this);*/
	
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
		return _Damage;
	}
	
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		//_AtackDelay++;
		Physis.Calculate(this);
		StateMachine.Execute();
		
		
		
	}
	@Override
	public void spawnTo(Player player) {
		AddEntityPacket pk = new AddEntityPacket();
		pk.type = this.getNetworkId();
		pk.entityUniqueId = this.getId();
		pk.entityRuntimeId = this.getId();
		pk.x = (float) this.x;
		pk.y = (float) this.y;
		pk.z = (float) this.z;
		pk.speedX = (float) this.motionX;
		pk.speedY = (float) this.motionY;
		pk.speedZ = (float) this.motionZ;
		pk.metadata = this.dataProperties;
		player.dataPacket(pk);
		super.spawnTo(player);
	}
	
}
