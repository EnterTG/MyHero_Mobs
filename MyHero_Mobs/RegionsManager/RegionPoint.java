package MyHero_Mobs.RegionsManager;

import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;

public class RegionPoint extends Region{

	private int x,y,z;
	private int ActivationRange = 25;
	
	
	public RegionPoint(int x,int y,int z)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public boolean playerInRegion(Player p) {
		return (p.getLocation().distance(new Vector3(x, y, z)) <= ActivationRange);
	}

	@Override
	public void generateSpawnPoints(List<Vector3> target) {
		target.add(new Vector3(x, y, z));
	}
	
	@Override
	public String toString()
	{
		return "X: "+x +" Y: " +y + " Z: " +z;
	}

}
