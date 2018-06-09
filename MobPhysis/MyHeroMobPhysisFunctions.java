package MobPhysis;

import cn.nukkit.math.Vector3;

public class MyHeroMobPhysisFunctions {

	
	public static MyHeroMobPhysis getWalkingPhysis()
	{
		return (mob) ->
		{
			Vector3 a= mob.getLocation().subtract(0, 0.1, 0);
			if(!mob.isOnGround())
				mob.move(a.x,a.y,a.z);
			
		};
	}
}
