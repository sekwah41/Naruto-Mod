package com.sekwah.narutomod.entity;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.util.StateUtils;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NarutoDataSerialisers {


    public static final EntityDataSerializer<AttachFace> ATTACH_FACE = new EntityDataSerializer<>() {
        public void write(FriendlyByteBuf buf, AttachFace value) {
            buf.writeByte(StateUtils.faceToByte(value));
        }

        public AttachFace read(FriendlyByteBuf buf) {
            return StateUtils.byteToFace(buf.readByte());
        }

        @Override
        public AttachFace copy(AttachFace value) {
            return value;
        }
    };

    public static final EntityDataSerializer<Direction> BLOCK_DIRECTION = new EntityDataSerializer<>() {
        public void write(FriendlyByteBuf buf, Direction value) {
            buf.writeByte(value.get2DDataValue());
        }

        public Direction read(FriendlyByteBuf buf) {
            return Direction.from2DDataValue(buf.readByte());
        }

        public Direction copy(Direction value) {
            return value;
        }
    };

    private static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, NarutoMod.MOD_ID);

    public static final RegistryObject<EntityDataSerializer> ATTACH_FACE_ENTRY = DATA_SERIALIZERS.register("attach_face", () -> ATTACH_FACE);

    public static final RegistryObject<EntityDataSerializer> BLOCK_DIRECTION_ENTRY = DATA_SERIALIZERS.register("block_direction", () -> BLOCK_DIRECTION);


    public static void register(IEventBus eventBus) {
        DATA_SERIALIZERS.register(eventBus);
    }


}
