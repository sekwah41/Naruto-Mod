package sekwah.mods.narutomod.common.entity;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.util.ResourceLocation;

public interface SkinCallback {
    void returnedSkin(MinecraftProfileTexture.Type p_152121_1_, ResourceLocation p_152121_2_);
}
