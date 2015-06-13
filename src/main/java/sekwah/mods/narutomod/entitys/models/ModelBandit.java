package sekwah.mods.narutomod.entitys.models;

import net.minecraft.client.model.ModelBiped;

public class ModelBandit extends ModelBiped {

    public ModelBandit() {
        this(0.0F, false);
    }

    public ModelBandit(float par1, boolean par2) {
        super(par1, 0.0F, 64, par2 ? 32 : 64);

        this.textureWidth = 64;
        this.textureHeight = 64;

    }

}
