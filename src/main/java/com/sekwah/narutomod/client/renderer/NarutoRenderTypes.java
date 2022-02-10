package com.sekwah.narutomod.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import org.lwjgl.opengl.GL11;

public class NarutoRenderTypes extends RenderType {

    public static final RenderType JUTSU_INFO = create("narutomod_jutsu_overlay",
            DefaultVertexFormat.POSITION_COLOR_TEX, VertexFormat.Mode.QUADS, 256/*bufferSize*/,
            false/*affectsCrumbling*/, false/*sortOnUpload*/, RenderType.CompositeState.builder()
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(CULL)
                    .createCompositeState(false)
    );

    public NarutoRenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
}
