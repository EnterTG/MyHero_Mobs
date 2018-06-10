package Mobs.Monster.Walking;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class Shulker extends de.kniffo80.mobplugin.entities.monster.walking.Shulker {

    public static final int NETWORK_ID = 54;

    public Shulker(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        //this.route = new WalkerRouteFinder(this);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }


    @Override
    public int getKillExperience() {
        return 5;
    }

}
