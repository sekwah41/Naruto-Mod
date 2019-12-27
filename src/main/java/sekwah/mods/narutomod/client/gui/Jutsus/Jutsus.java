package sekwah.mods.narutomod.client.gui.Jutsus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;

public class Jutsus extends StatBase {
    /**
     * Is the column (related to center of Jutsus gui, in 24 pixels unit) that the Jutsus will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of Jutsus gui, in 24 pixels unit) that the Jutsus will be displayed.
     */
    public final int displayRow;

    /**
     * Holds the parent Jutsus, that must be taken before this Jutsus is avaiable.
     */
    public final Jutsus parentJutsus;
    /**
     * Holds the ItemStack that will be used to draw the Jutsus into the GUI.
     */
    public final ItemStack theItemStack;
    /**
     * Holds the description of the Jutsus, ready to be formatted and/or displayed.
     */
    private final String JutsusDescription;
    public final String JUTSU_COMBO;
    @SideOnly(Side.CLIENT)

    /**
     * Holds a string formatter for the Jutsus, some of then needs extra dynamic info - like the key used to open
     * the inventory.
     */
    private IStatStringFormat statStringFormatter;
    /**
     * Special Jutsuss have a 'spiked' (on normal texture pack) frame, special Jutsuss are the hardest ones to
     * achieve.
     */
    private boolean isSpecial;

    public Jutsus(String par1Str, String par2Str, int par3, int par4, Item par5Item, Jutsus par6Jutsus) {
        this(par1Str, par2Str, par3, par4, new ItemStack(par5Item), par6Jutsus);
    }

    public Jutsus(String par1Str, String par2Str, int par3, int par4, Block par5Block, Jutsus par6Jutsus) {
        this(par1Str, par2Str, par3, par4, new ItemStack(par5Block), par6Jutsus);
    }

    public Jutsus(String par1Str, String par2Str, int par3, int par4, ItemStack par5ItemStack, Jutsus par6Jutsus) {
        super(par1Str, new ChatComponentTranslation("Jutsus." + par2Str));
        this.theItemStack = par5ItemStack;
        this.JutsusDescription = "Jutsus." + par2Str + ".desc";
        this.JUTSU_COMBO = "Jutsus." + par2Str + ".combo";
        this.displayColumn = par3;
        this.displayRow = par4;

        if (par3 < JutsuList.minDisplayColumn) {
            JutsuList.minDisplayColumn = par3;
        }

        if (par4 < JutsuList.minDisplayRow) {
            JutsuList.minDisplayRow = par4;
        }

        if (par3 > JutsuList.maxDisplayColumn) {
            JutsuList.maxDisplayColumn = par3;
        }

        if (par4 > JutsuList.maxDisplayRow) {
            JutsuList.maxDisplayRow = par4;
        }

        this.parentJutsus = par6Jutsus;
    }

    /**
     * Indicates whether or not the given Jutsus or statistic is independent (i.e., lacks prerequisites for being
     * update).
     */
    public Jutsus setIndependent() {
        this.isIndependent = true;
        return this;
    }

    /**
     * Special Jutsuss have a 'spiked' (on normal texture pack) frame, special Jutsuss are the hardest ones to
     * achieve.
     */
    public Jutsus setSpecial() {
        this.isSpecial = true;
        return this;
    }

    /**
     * Adds the Jutsus on the internal list of registered Jutsuss, also, it's check for duplicated id's.
     */
    public Jutsus registerJutsus() {
        super.registerStat();
        JutsuList.JutsusList.add(this);
        return this;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns whether or not the StatBase-derived class is a statistic (running counter) or an Jutsus (one-shot).
     */
    public boolean isJutsus() {
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the fully description of the Jutsus - ready to be displayed on screen.
     */
    public String getDescription() {
        return this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.JutsusDescription)) : StatCollector.translateToLocal(this.JutsusDescription);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Defines a string formatter for the Jutsus.
     */
    public Jutsus setStatStringFormatter(IStatStringFormat par1IStatStringFormat) {
        this.statStringFormatter = par1IStatStringFormat;
        return this;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Special Jutsuss have a 'spiked' (on normal texture pack) frame, special Jutsuss are the hardest ones to
     * achieve.
     */
    public boolean getSpecial() {
        return this.isSpecial;
    }

    /**
     * Register the stat into StatList.
     */
    public StatBase registerStat() {
        return this.registerJutsus();
    }

    /**
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public StatBase initIndependentStat() {
        return this.setIndependent();
    }
}