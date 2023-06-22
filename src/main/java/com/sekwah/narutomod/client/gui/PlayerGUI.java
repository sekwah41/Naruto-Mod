package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix4f;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public interface PlayerGUI {
    void render(GuiGraphics guiGraphics, Matrix4f worldMatrix, Vec3 cameraPos);
    void tick(Player player);
}
