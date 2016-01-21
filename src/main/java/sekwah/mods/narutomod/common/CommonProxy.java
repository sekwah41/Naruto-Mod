package sekwah.mods.narutomod.common;

import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.network.UsageReport;

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
}
