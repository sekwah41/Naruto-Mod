package sekwah.mods.narutomod.client.gui;

import net.minecraft.util.ResourceLocation;
import sekwah.mods.narutomod.NarutoMod;

/**
 * Created on 21/12/2015.
 *
 * @author sekwah41
 */
public class NarutoResource extends ResourceLocation {

    public final int defaultWidth;

    public final int defaultHeight;

    public NarutoResource(String textureLocation, int defaultWidth, int defaultHeight) {
        super(NarutoMod.modid, textureLocation);
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
    }
}
