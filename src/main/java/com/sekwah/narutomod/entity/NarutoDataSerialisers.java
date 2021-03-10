package com.sekwah.narutomod.entity;

import com.sekwah.narutomod.util.StateUtils;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoDataSerialisers {


    public static final IDataSerializer<AttachFace> ATTACH_FACE = new IDataSerializer<AttachFace>() {
        public void write(PacketBuffer buf, AttachFace value) {
            buf.writeByte(StateUtils.faceToByte(value));
        }

        public AttachFace read(PacketBuffer buf) {
            return StateUtils.byteToFace(buf.readByte());
        }

        @Override
        public AttachFace copy(AttachFace value) {
            return value;
        }
    };

    public static final IDataSerializer<Direction> BLOCK_DIRECTION = new IDataSerializer<Direction>() {
        public void write(PacketBuffer buf, Direction value) {
            buf.writeByte(value.get2DDataValue());
        }

        public Direction read(PacketBuffer buf) {
            return Direction.from2DDataValue(buf.readByte());
        }

        public Direction copy(Direction value) {
            return value;
        }
    };

    private static final DeferredRegister<DataSerializerEntry> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.DATA_SERIALIZERS, MOD_ID);

    public static final RegistryObject<DataSerializerEntry> ATTACH_FACE_ENTRY = DATA_SERIALIZERS.register("attach_face", () -> new DataSerializerEntry(ATTACH_FACE));

    public static final RegistryObject<DataSerializerEntry> BLOCK_DIRECTION_ENTRY = DATA_SERIALIZERS.register("block_direction", () -> new DataSerializerEntry(BLOCK_DIRECTION));


    public static void register(IEventBus eventBus) {
        DATA_SERIALIZERS.register(eventBus);
    }


}
