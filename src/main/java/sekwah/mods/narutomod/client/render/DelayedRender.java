package sekwah.mods.narutomod.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class DelayedRender {

    private final IDelayedRender renderer;
    private final Entity entity;
    private final double posX;
    private final double posY;
    private final double posZ;
    private final float speed;
    private final float delta;

    public DelayedRender(IDelayedRender renderer, Entity entity, double posX, double posY, double posZ, float speed, float delta) {
        this.renderer = renderer;
        this.entity = entity;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.speed = speed;
        this.delta = delta;
    }

    public void render() {
        this.renderer.delayedRender(entity, posX, posY, posZ, speed, delta);
    }
}
