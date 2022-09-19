package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.network.c2s.ServerToggleNinjaPacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.concurrent.atomic.AtomicBoolean;

public class JutsuScreen extends Screen {
    public JutsuScreen() {
        super(Component.translatable("naruto.gui.jutsu.title"));
    }

    private Button becomeANinja;
    private Button changeBack;

    private Component[] renderLines = {
            Component.translatable("naruto.gui.jutsu.notice"),
            Component.translatable("naruto.gui.jutsu.notice2"),
            Component.empty(),
            Component.translatable("naruto.gui.jutsu.placeholder"),
    };

    protected void init() {
        this.addButtons();
    }

    public void addButtons() {

        becomeANinja = this.addRenderableWidget(new Button(this.width / 2 - 102, this.height / 4 + 72 + -16, 98, 20, Component.translatable("naruto.gui.jutsu.enable"), (buttonRef) -> {
            PacketHandler.sendToServer(new ServerToggleNinjaPacket(true));
        }));
        renderables.add(becomeANinja);
        becomeANinja.active = false;

        changeBack = this.addRenderableWidget(new Button(this.width / 2 + 4, this.height / 4 + 72 + -16, 98, 20, Component.translatable("naruto.gui.jutsu.disable"), (buttonRef) -> {
            PacketHandler.sendToServer(new ServerToggleNinjaPacket(false));
        }));
        renderables.add(changeBack);
        changeBack.active = false;

        Button back = this.addRenderableWidget(new Button(this.width / 2 - 98 / 2, this.height / 4 + 72 + 7, 98, 20, Component.translatable("naruto.gui.jutsu.done"), (buttonRef) -> {
            this.minecraft.popGuiLayer();
        }));
        renderables.add(back);
    }

    public void tick() {
        super.tick();
        AtomicBoolean isNinja = new AtomicBoolean(false);
        var player = this.minecraft.player;
        if(player != null) {
            player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
                isNinja.set(ninjaData.isNinjaModeEnabled());
            });
        }
        becomeANinja.active = !isNinja.get();
        changeBack.active = isNinja.get();
    }

    public boolean isPauseScreen() {
        return false;
    }

    public void render(PoseStack poseStack, int p_96311_, int p_96312_, float p_96313_) {
        this.renderBackground(poseStack);
        drawCenteredString(poseStack, this.font, this.title, this.width / 2, 40, 16777215);
        for (int i = 0; i < renderLines.length; i++) {
            drawCenteredString(poseStack, this.font, renderLines[i], this.width / 2, 60 + 10 * i, 16777215);
        }
        super.render(poseStack, p_96311_, p_96312_, p_96313_);
    }
}
