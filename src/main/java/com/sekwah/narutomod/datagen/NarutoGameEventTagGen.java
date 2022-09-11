package com.sekwah.narutomod.datagen;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.gameevents.NarutoGameEvents;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.GameEventTagsProvider;
import net.minecraft.tags.GameEventTags;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NarutoGameEventTagGen extends GameEventTagsProvider {
    public NarutoGameEventTagGen(DataGenerator p_176826_, ExistingFileHelper existingFileHelper) {
        super(p_176826_, NarutoMod.MOD_ID, existingFileHelper);
    }

    private void soundDetected(GameEvent event) {
        this.tag(GameEventTags.WARDEN_CAN_LISTEN).add(event);
        this.tag(GameEventTags.VIBRATIONS).add(event);
        this.tag(GameEventTags.SHRIEKER_CAN_LISTEN).add(event);
    }

    protected void addTags() {
        soundDetected(NarutoGameEvents.JUTSU_CASTING.get());
        soundDetected(NarutoGameEvents.DOUBLE_JUMP.get());
        soundDetected(NarutoGameEvents.LEAP.get());
    }

}
