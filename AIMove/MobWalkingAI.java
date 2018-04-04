package AIMove;

import Core.MyHeroMain;
import MobAIInterface.Move;
import MobManager.MyHeroMob;
import cn.nukkit.Nukkit;

import java.util.logging.Logger;

public class MobWalkingAI implements Move
{
	private MyHeroMob Entity;
	public MobWalkingAI(MyHeroMob MyHeroMob)
	{
		Entity = MyHeroMob;
	}
	@Override
	public void Move()
	{
		if(Entity.getTarget() != null)
		{
		
		}
		else
		{
			//Entity.x += 1;
			MyHeroMain.Main.getLogger().info("My pos" + Entity.getLocation());
		}
		
		//Move();
	}
	
	
	
}
