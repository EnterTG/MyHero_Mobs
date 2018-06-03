package AIMove;

import MobManager.MyHeroMob;
import StateMachine.State;
import StateMachine.StateMachineCore;
import StateMachine.Triggers;

public class MobWalkingAI implements State
{
	private MyHeroMob Entity;
	private StateMachineCore Machine;
	
	private final int FollowDistance = 15;
	public MobWalkingAI(MyHeroMob entity,StateMachineCore machinecore)
	{
		Machine = machinecore;
		Entity = entity;
	}
	public void Move()
	{
	/*	if(Entity.getTarget() != null)
		{
		
		}
		else
		{
			//Entity.x += 1;
			MyHeroMain.Main.getLogger().info("My pos" + Entity.getLocation());
		}
		*/
		//Move();
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		if(!targetInRange())
			return;
		if(HandlePath())
			return;
		
	}
	
	public boolean targetInRange()
	{
		if(Entity.getTarget().getLocation().distance(Entity.getLocation()) > FollowDistance)
		{
			Machine.Fire(Triggers.TargerLost);
			return false;
		}
		else
			return true;
	}
	
	public boolean HandlePath()
	{
		if(Entity.getTarget() != null)
		{
			
			return false;
		}
		else
		{
			Machine.Fire(Triggers.TargetDead);
			return true;
		}
	}
	
	
}
