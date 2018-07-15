package MyHero_Mobs.RegionsManager;
import java.util.List;
import java.util.Random;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector2;
import cn.nukkit.math.Vector3;

public class RegionSquare extends Region{

	private Vector2 p1,p2;
	
	
	
	
	public Vector2 getP1() {
		return p1;
	}
	public void setP1(Vector2 p1) {
		this.p1 = p1;
	}
	public Vector2 getP2() {
		return p2;
	}
	public void setP2(Vector2 p2) {
		this.p2 = p2;
	}
	
	private float area(double x1, double y1, double x2,  double y2, double x3, double y3)
	{
		return (float)Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}
	private boolean check(int x, int y)
	{
		float A = area(p1.x, p1.y, p2.x, p1.y, p2.x, p2.y)+  area(p1.x, p1.y, p1.x, p2.y, p2.x, p2.y);

		float A1 = area(x, y, p1.x, p1.y, p2.x, p1.y);

		float A2 = area(x, y, p2.x, p1.y,p2.x, p2.y);

		float A3 = area(x, y, p2.x, p2.y, p1.x, p2.y);

		float A4 = area(x, y, p1.x, p1.y,  p1.x, p2.y);

		return (A == A1 + A2 + A3 + A4);
	}
	
	@Override
	public boolean playerInRegion(Player p)
	{
		Location playerloc = p.getLocation();
		if(playerloc.level != world) return false;
		return check((int)playerloc.x,(int)playerloc.y);
	}
	
	@Override
	public String toString()
	{
		return "P1: x = " + p1.x + " z = " + p1.y + " P2: x = " + p2.x + " z = " + p2.y+ " Level: " +world.getName();
	}

	
	
	public void getPossitions(List<Vector3> t,int x,int z,int retry)
	{
		for(int y = 255;y > 0;y--)
		{
			if(SpawnOn.contains(getWorld().getBlockIdAt(x, y, z)) && getWorld().getBlockIdAt(x, y+1, z) == 0 && getWorld().getBlockIdAt(x, y+2, z) == 0 )
			{
				t.add(new Vector3(x, y, z));
				if(SpawninRooms) continue;
				else return;
			}
		}
		if(retry != MaxRetry)
		{
			Random r = new Random();
			x +=  r.nextInt(16)-8;
			z += r.nextInt(16)-8;
			getPossitions(t,x,z,++retry);
		}
	}
	@Override
	public void generateSpawnPoints(List<Vector3> target) {

		int xmin = (int) Math.min(getP1().x, getP2().x);
		int xmax = (int) Math.max(getP1().x, getP2().x);
		
		int ymin = (int) Math.min(getP1().y, getP2().y);
		int ymax = (int) Math.max(getP1().y, getP2().y);
		
		
		int denst = 16/Density;
		for(int x = xmin ; x < xmax;x+=denst)
		{
			for(int y = ymin ; y < ymax;y+=denst)
			{
				getPossitions(target,x,y,0);
			}
		}
		
	}
	
}
