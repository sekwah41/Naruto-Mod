Blockbench Help
=========

# Blockbench models
When saving the blockbench models you will have missing imports and package name. Make sure to re-add them.
```java
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
```
Also use RenderType.entityCutoutNoCull for if you want the mob to render while your camera is inside the mob.

It also better represets what youll see from blockbench a lot more especially for 0 thick cubes.
