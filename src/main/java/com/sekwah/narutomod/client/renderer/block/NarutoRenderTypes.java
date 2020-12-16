package com.sekwah.narutomod.client.renderer.block;

import com.sekwah.narutomod.block.NarutoBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class NarutoRenderTypes {


    public static void register() {
        RenderTypeLookup.setRenderLayer(NarutoBlocks.PAPER_BOMB.get(), RenderType.getTranslucent());
    }
}
