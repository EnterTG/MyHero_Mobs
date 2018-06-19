package MyHero_Mobs.MobManager;

import java.util.HashMap;
import java.util.Map;

public enum MobsType {

	Bat(19),Parrot(30),Rabbit(18),Dolphin(31),Squid(17),Chicken(10),Cow(11)
	,Donkey(24),Horse(23),Llama(29),Mooshroom(16),Mule(25),Ocelot(22),Pig(12),PolarBear(28)
	,Sheep(13),SkeletonHorse(26),Villager(15),ZombieHorse(27),Blaze(43),EnderDragon (53)
	,Ghast(41),Vex(105),Wither (52),MagmaCube(42),Slime(37),ElderGuardian(50),Guardian(49)
	,CaveSpider(40),Creeper(33),Enderman(38),Endermite(55),Evoker(104),Skeleton(34)
	,Husk(47),IronGolem(20),PigZombie(36),Shulker(54),Silverfish(39),SnowGolem(21),Spider(35)
	,Stray(46),Vindicator(57),Witch(45),WitherSkeleton(48),Wolf(14),Zombie(32),ZombieVillager(44);
	
	public final int ID;
	MobsType(int t)
	{
		ID =t;
	}
	
	private static final Map<Integer,MobsType> map;
	static {
		map = new HashMap<Integer,MobsType>();
		for (MobsType v : MobsType.values()) 
			map.put(v.ID, v);
		
	}
	public static MobsType findByKey(int i) {
		return map.get(i);
	}
}
