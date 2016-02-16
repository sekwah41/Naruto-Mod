package sekwah.mods.narutomod.common.potioneffects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PotionChakraRestore extends Potion {

public PotionChakraRestore(int par1, boolean par2, int par3) {
super(par1, par2, par3);
}

public Potion setIconIndex(int par1, int par2) {
super.setIconIndex(par1, par2);
return this;
}

    private static final ResourceLocation potionTextures = new ResourceLocation("narutomod:textures/gui/inventory.png");

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(potionTextures);
        return true;
    }
}