package Mobs.Monster.Walking;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import de.kniffo80.mobplugin.route.WalkerRouteFinder;

public class WitherSkeleton extends de.kniffo80.mobplugin.entities.monster.walking.WitherSkeleton {

    public static final int NETWORK_ID = 48;

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    public WitherSkeleton(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.route = new WalkerRouteFinder(this);
    }


    @Override
    public int getKillExperience() {
        return 5;
    }
}
