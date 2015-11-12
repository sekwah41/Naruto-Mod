package com.sekwah.mods.narutomod.generic;

import com.sekwah.mods.narutomod.NarutoMod;
import com.sekwah.mods.narutomod.network.UsageReport;

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
