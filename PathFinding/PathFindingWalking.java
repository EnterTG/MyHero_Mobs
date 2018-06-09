package PathFinding;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import Core.MyHeroMain;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

public class PathFindingWalking {

	private final Location Start,End;
	private final Level World;
	//private final Entity source;
	private Stack<Location> Path;
	
	public PathFindingWalking(Location StartPoint,Location EndPoint)
	{
		Start = StartPoint;
		End = EndPoint;
		
		Start.round();
		End.round();
		
		//source = Source;
		World = StartPoint.level;
	}
	public Location nextPoint()
	{
		return Path.pop();
	}
	public void Calculate() 
	{
		MyHeroMain.Main.getLogger().info("Obliczam sciezke");
		List<Location> ClosedSet = new ArrayList<Location>();
		List<Location> OpenSet = new ArrayList<Location>();
		
		OpenSet.add(Start);
		
		Map<Location,Location> CameFrom = new HashMap<Location,Location>();
		Map<Location,Double> GScore = new HashMap<Location,Double>();
		
		GScore.put(Start, 0.0);
		
		Map<Location,Double> FScore = new HashMap<Location,Double>();
		
		FScore.put(Start, Start.distance(End));
		long TimeStart = System.currentTimeMillis();
		while(!OpenSet.isEmpty())
		{
			if(TimeStart < System.currentTimeMillis() + 500)
				break;
			Location current = getMinKey(FScore);
			
			if(current.equals(End))
				ReconstructPath(CameFrom,current);
			
			OpenSet.remove(current);
			ClosedSet.add(current);
			
			for(Location nei : getNeighbor(current))
			{
				if(ClosedSet.contains(nei))
					continue;
				if(!OpenSet.contains(nei))
					OpenSet.add(nei);
				
				double tengscore = GScore.get(current) + current.distance(nei);
				if(tengscore >= GScore.get(nei))
					continue;
				
				CameFrom.put(nei, current);
				GScore.put(nei, tengscore);
				FScore.put(nei, tengscore + nei.distance(End));
			}
		}
	}
	
	private List<Location> getNeighbor(Location source)
	{
		List<Location> neighbor = new ArrayList<>();
		for(int x = -1; x <= 1 ;x++)		
			for(int y = -1; y <= 1 ;y++)
				if(World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z) == 0)
					if(World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z-1) != 0)
						if(World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z+1) == 0 && World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z+2) == 0)
							neighbor.add(new Location((int)source.x + x,(int)source.y + y,(int)source.z-1,World));
				else
					if(World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z+1) == 0 && World.getBlockIdAt((int)source.x + x,(int) source.y + y, (int)source.z+2) == 0)
						neighbor.add(new Location((int)source.x + x,(int)source.y + y,(int)source.z,World));
		return neighbor;
	}
	
	private Location getMinKey(Map<Location,Double> source)
	{
		Entry<Location,Double> min = null;
		for (Entry<Location, Double> entry : source.entrySet()) 
			if (min == null || min.getValue() > entry.getValue()) 
				min = entry;
		return min.getKey();
	}

	private void ReconstructPath(Map<Location,Location> camefrom,Location current)
	{
		MyHeroMain.Main.getLogger().info("Konstruluje sciezke");
		Path.push(current);
		Location tmp = current;
		while(camefrom.containsKey(tmp))
		{
			tmp = camefrom.get(tmp);
			Path.push(tmp);
		}
	}
}
