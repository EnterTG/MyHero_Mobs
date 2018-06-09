package AITargetSelector;

import java.util.Map;

import MobManager.MyHeroMob;
import StateMachine.State;
import StateMachine.StateMachineCore;
import StateMachine.Triggers;
import cn.nukkit.Player;
import cn.nukkit.level.Location;

public class MobTargetSelector implements State
{
	
	private MyHeroMob Entity;
	private StateMachineCore Machine;
	
	public MobTargetSelector(MyHeroMob entity,StateMachineCore machinecore)
	{
		Machine = machinecore;
		Entity = entity;
	}
	
	public boolean  FindTarget()
	{
		Map<Integer,Player> PlayersViewers = Entity.getViewers();
		double CloserPlayerDistance = 11;//= Collections.min(PlayersViewers.keySet());
		int KeyPlayer = -1;
		Location EntityLocation = Entity.getLocation();
		for(Map.Entry<Integer,Player> p : PlayersViewers.entrySet())
		{
			double Distance = EntityLocation.distance(p.getValue().getLocation());
			if(Distance < CloserPlayerDistance)
			{
				KeyPlayer = p.getKey();
				CloserPlayerDistance = Distance;
			}
		}
		if(CloserPlayerDistance > 10)
			return false;
		else
			Entity.setTarget(PlayersViewers.get(KeyPlayer));
		return true;
	}
// TODO Auto-generated method stub
	@Override
	public void Execute() 
	{
		if(FindTarget())
			Machine.Fire(Triggers.TargetFound);
		else
			Machine.Fire(Triggers.Wait);
		
	}
	
	
}
