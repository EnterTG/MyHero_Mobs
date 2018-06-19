package MyHero_Mobs.SpawningMamager;
import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector2;

public class Region {

	private Vector2 p1,p2;
	private Level world;
	
	
	
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
	public Level getWorld() {
		return world;
	}
	public void setWorld(Level world) {
		this.world = world;
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
	
	
	public boolean PlayerInRegion(Player p)
	{
		Location playerloc = p.getLocation();
		if(playerloc.level != world) return false;
		return check((int)playerloc.x,(int)playerloc.y);
	}
}
