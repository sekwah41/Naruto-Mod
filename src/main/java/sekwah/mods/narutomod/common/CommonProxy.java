package sekwah.mods.narutomod.common;

import com.mojang.authlib.GameProfile;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.entity.SkinCallback;
import sekwah.mods.narutomod.items.NarutoItems;
import sekwah.mods.narutomod.network.UsageReport;
import sekwah.mods.narutomod.settings.NarutoSettings;

// TODO start moving more code into here and use super.function to register both sides. Itll make stuff more organised.
public class CommonProxy {

    public void addKeyBindings() {
    }

    public boolean isClient(){
        return false;
    }

    public void registerRenderers() {
    }

    public void addTickHandelers() {


    }

    public void getSkin(GameProfile profile, SkinCallback entity) {
    }

    public void addEventListener() {

    }

    public void addInGameGUIs() {
    }

    public void registerCustomItems() {
    }

    public void addModderCapes() {
    }

    public void registerCustomBlocks() {

    }

    public void startUsageReport() {
        NarutoMod.usageReport = new UsageReport(false);
        NarutoMod.usageReport.startUsageReport();
    }

    public void addItems() {
        NarutoItems.addItems(NarutoSettings.config);
    }

    public void commands() {

    }
}
