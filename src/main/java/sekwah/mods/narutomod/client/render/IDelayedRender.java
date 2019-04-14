package sekwah.mods.narutomod.client.render;

import net.minecraft.entity.Entity;

public interface IDelayedRender {

    void delayedRender(Entity entity, double posX, double posY, double posZ, float speed, float delta);

}
