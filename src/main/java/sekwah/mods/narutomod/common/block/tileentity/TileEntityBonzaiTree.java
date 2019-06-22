package sekwah.mods.narutomod.common.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBonzaiTree extends TileEntity {
    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
    }

    /**public Packet getDescriptionPacket() { not needed at the moment
     NBTTagCompound nbtTag = new NBTTagCompound();
     this.writeToNBT(nbtTag);
     return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
     }

     public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
     readFromNBT(packet.customParam1);
     }*/
}