package sekwah.mods.narutomod.generic;

import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.network.UsageReport;

public class CommonProxy {

    public void addKeyBindings() {
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
