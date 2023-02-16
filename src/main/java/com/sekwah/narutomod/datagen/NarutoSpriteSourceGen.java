package com.sekwah.narutomod.datagen;

import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public class NarutoSpriteSourceGen extends SpriteSourceProvider {
    public NarutoSpriteSourceGen(PackOutput output, ExistingFileHelper fileHelper, String modid) {
        super(output, fileHelper, modid);
    }

    @Override
    protected void addSources() {
        this.atlas(BLOCKS_ATLAS)
                .addSource(addFolder("blocks/weapons"))
                .addSource(addFolder("blocks/deco"))
                .addSource(addFolder("items"));
    }

    private DirectoryLister addFolder(String folderName) {
        return new DirectoryLister(folderName, folderName + "/");
    }
}
