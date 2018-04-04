package AITargetSelector;

import MobManager.MyHeroMob;
import cn.nukkit.Player;
import cn.nukkit.level.Location;

import java.util.Map;

public class MobTargetSelector
{
	
	private MyHeroMob Entity;
	
	public void FindTarget()
	{
		Map<Integer,Player > PlayersViewers = Entity.getViewers();
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
			return;
		else
			Entity.setTarget(PlayersViewers.get(KeyPlayer));
	}
	
	
}
