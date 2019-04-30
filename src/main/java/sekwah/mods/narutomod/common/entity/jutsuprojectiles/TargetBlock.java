package sekwah.mods.narutomod.common.entity.jutsuprojectiles;

import net.minecraft.block.Block;

public class TargetBlock implements Comparable<TargetBlock> {

    public final int x;
    public final int y;
    public final int z;
    public final double sqDist;

    public TargetBlock(int x, int y, int z, double bx, double by, double bz) {
        this.x = x;
        this.y = y;
        this.z = z;
        double dx = bx - x;
        double dy = by - y;
        double dz = bz - z;
        this.sqDist = dx * dx + dy * dy + dz * dz;
    }

    @Override
    public int compareTo(TargetBlock compare) {
        return Double.compare(this.sqDist, compare.sqDist);
    }
}
