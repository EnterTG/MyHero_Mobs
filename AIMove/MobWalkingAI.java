package AIMove;

import Core.MyHeroMain;
import MobManager.MyHeroMob;
import PathFinding.PathFindingWalking;
import StateMachine.State;
import StateMachine.StateMachineCore;
import StateMachine.Triggers;
import cn.nukkit.level.Location;

public class MobWalkingAI implements State
{
	private MyHeroMob Entity;
	private StateMachineCore Machine;
	
	private PathFindingWalking Path;
	private final int FollowDistance = 15;
	 
	public MobWalkingAI(MyHeroMob entity,StateMachineCore machinecore)
	{
		Machine = machinecore;
		Entity = entity;
	}
	public void Move()
	{
		/*Entity.addMovement( B, y, z, yaw, pitch, headYaw);
		Entity.addMotion(motionX, motionY, motionZ);*/
		Location pathpoint = Path.nextPoint();
		if(pathpoint != null)
			Entity.move(pathpoint.x, pathpoint.y, pathpoint.z);
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		if(HandleTarget())
			return;
		if(!targetInRange())
			return;
		if(HandlePath())
			return;
		Move();
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
		if(Path == null)
			Path = new PathFindingWalking(Entity.getLocation(),Entity.getTarget().getLocation());
		if(Path == null)
		{
			Machine.Fire(Triggers.Wait);
			return true;
		}
		Path.Calculate();
		return false;
	}
	public boolean HandleTarget()
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
