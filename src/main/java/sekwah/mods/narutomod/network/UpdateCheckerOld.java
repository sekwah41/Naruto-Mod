package sekwah.mods.narutomod.network;

import sekwah.mods.narutomod.NarutoMod;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public final class UpdateCheckerOld {

    public static String updatestatus = "checking";
    public static String updatetext = EnumChatFormatting.YELLOW + " - Checking for update...";
    public static boolean joinenabled = false;
    public static String serverip = "localhost";
    public static int serverport = 25565;

    public static String servertext = "Join Naruto Server";

    public UpdateCheckerOld() {
        updatestatus = "checking";
        checkUpdate();
    }

    /**
     * @return
     * @Override public void run() {
     */
    public void checkUpdate() {

        String narutoversion = NarutoMod.version;

        try {
            NarutoMod.logger.info("Checking for updates...");
            URL url = new URL("http://www.sekwah.com/naruto-mod/UpdateInfo.txt");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            Scanner s = new Scanner(connection.getInputStream());
            int mcversion = Integer.parseInt(s.nextLine());
            int modversion = Integer.parseInt(s.nextLine());
            String newestdownloadlink = s.nextLine();
            s.close();

            if (mcversion == NarutoMod.mcversion && modversion == NarutoMod.modversion) {
                if (NarutoMod.isPreRelease) {
                    updatetext = EnumChatFormatting.YELLOW + " - The official release is now available!";
                    updatestatus = "updated";
                    NarutoMod.logger.info("Current copy is up to date.");
                } else {
                    updatetext = EnumChatFormatting.GREEN + " - The Naruto mod is up to date.";
                    updatestatus = "updated";
                    NarutoMod.logger.info("Current copy is up to date.");
                }
            } else if (mcversion <= NarutoMod.mcversion && modversion <= NarutoMod.modversion) {
                updatetext = EnumChatFormatting.AQUA + " - This is a pre release!";
                updatestatus = "updated";
                NarutoMod.logger.info("Current copy is a pre-release.");
            } else {
                updatetext = EnumChatFormatting.GOLD + " - An update is available!";
                updatestatus = "update";
                NarutoMod.logger.info("Update found.");
            }
        } catch (IOException ex) {
            updatetext = EnumChatFormatting.RED + " - Could not connect to the update info file :(";
        } catch (NumberFormatException ex) {
            updatetext = EnumChatFormatting.RED + " - Something is wrong with the update file.";
        }

        try {
            URL url = new URL("https://www.dropbox.com/s/staineiwde1azr8/Current%20Server%20IP.txt?dl=1");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(4000);
            Scanner s = new Scanner(connection.getInputStream());
            joinenabled = Boolean.parseBoolean(s.nextLine());
            serverip = s.nextLine();
            serverport = Integer.parseInt(s.nextLine());
            servertext = s.nextLine();
            s.close();
        } catch (IOException ex) {
            joinenabled = false;
        } catch (NumberFormatException ex) {
            joinenabled = false;
        }

    }

}
