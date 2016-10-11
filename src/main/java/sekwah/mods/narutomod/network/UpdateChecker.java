package sekwah.mods.narutomod.network;

import sekwah.mods.narutomod.NarutoMod;
import net.minecraft.util.EnumChatFormatting;
import sekwah.mods.narutomod.json.JSONException;
import sekwah.mods.narutomod.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sekwah on 22/10/2015.
 *
 * TODO change update detector to gson library and also the animations to gson and remove the json library.
 *
 * TODO Change back to a thread
 */
public class UpdateChecker/* extends Thread*/ {

    //public static String updatestatus = "checking";
    public static String updatetext = EnumChatFormatting.YELLOW + " - Checking for update";
    public static boolean joinenabled = false;
    public static String serverip = "localhost";
    public static int serverport = 25565;

    public static String servertext = "Join Naruto Server";

    private final Timer threadTrigger = new Timer("Update Checker", true);

    private final URL updateURL;

    // Once this is complete there will be a lot more data complete
    public UpdateChecker() {
        try {
            this.updateURL = new URL("http://report.sekwah.com/naruto-mod/updateCheck.php");
        } catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }
        //this.setName("Naruto Mod Update Detector");
        //this.setDaemon(true);
    }

    public void startUpdateChecker() {

            this.threadTrigger.schedule(new TimerTask() {

                public void run() {
                    NarutoMod.logger.info("Checking for updates");
                    checkUpdate(updateURL);
                }
            }, 0L); // Shorter than 15 mins by 10 seconds just to make sure its not on the border and online

            // players are missed

    }

    /**
     * Recode to use gson
     * @param url
     */
    public void checkUpdate(URL url) {
        NarutoMod.logger.info("Checking for update");
        try {
            InputStream in = url.openStream();
            String json = readJSONFileStream(in);
            JSONObject updateFile = new JSONObject(json);
            JSONObject mcVersion = updateFile.getJSONObject("mc");
            int mcMajor = mcVersion.getInt("major");
            int mcMinor = mcVersion.getInt("minor");
            int mcPatch = mcVersion.getInt("patch");
            JSONObject modVersion = updateFile.getJSONObject("mod");
            int modMajor = modVersion.getInt("major");
            int modMinor = modVersion.getInt("minor");
            int modPatch = modVersion.getInt("patch");
            //int mcVersion = updateFile.getInt("mcVersion");
            //int modVersion = updateFile.getInt("modVersion");
            String newestDownload = updateFile.getString("updateLink");

            if(mcMajor == NarutoMod.mcVersion[0] && mcMinor == NarutoMod.mcVersion[1] && mcPatch == NarutoMod.mcVersion[2]
                    && modMajor == NarutoMod.modVersion[0] && modMinor == NarutoMod.modVersion[1] && modPatch == NarutoMod.modVersion[2]){
                if (NarutoMod.isPreRelease) {
                    updatetext = EnumChatFormatting.YELLOW + " - The official release is now available!";
                } else {
                    updatetext = EnumChatFormatting.GREEN + " - The Naruto mod is up to date.";
                }
                NarutoMod.logger.info("Current copy is up to date.");
            }
            else if(mcMajor == NarutoMod.mcVersion[0] && mcMinor == NarutoMod.mcVersion[1] && mcPatch == NarutoMod.mcVersion[2]){
                if(modMajor > NarutoMod.modVersion[0]){

                    updatetext = EnumChatFormatting.GOLD + " - A major update is available!";
                    NarutoMod.logger.info("Update found.");
                }
                else if(modMinor > NarutoMod.modVersion[1]){
                    updatetext = EnumChatFormatting.GOLD + " - An update is available!";
                    NarutoMod.logger.info("Update found.");
                }
                else if(modPatch > NarutoMod.modVersion[2]){
                    updatetext = EnumChatFormatting.GOLD + " - A bugfix is available!";
                    NarutoMod.logger.info("Update found.");
                }
                else{
                    if (NarutoMod.isPreRelease) {
                        updatetext = EnumChatFormatting.AQUA + " - This is a pre-release";
                        NarutoMod.logger.info("Pre release?");
                    }
                    else{
                        updatetext = EnumChatFormatting.AQUA + " - This seems to be the next update";
                        NarutoMod.logger.info("Possibly next update");
                    }
                }
            }
            else{
                if(mcMajor > NarutoMod.mcVersion[0]
                        || (mcMajor == NarutoMod.mcVersion[0] && mcMinor > NarutoMod.mcVersion[1])
                        || (mcMajor == NarutoMod.mcVersion[0] && mcMinor == NarutoMod.mcVersion[1] && mcPatch > NarutoMod.mcVersion[2])){
                    updatetext = EnumChatFormatting.GOLD + " - Now available for " + mcMajor + "." + mcMinor + "." + mcPatch + "!";
                    NarutoMod.logger.info("Update found.");
                }
            }

            /*if (mcVersion == NarutoMod.mcVersion && modversion == NarutoMod.modVersion) {
                if (NarutoMod.isPreRelease) {
                    updatetext = EnumChatFormatting.YELLOW + " - The official release is now available!";
                    updatestatus = "updated";
                    NarutoMod.logger.info("Current copy is up to date.");
                } else {
                    updatetext = EnumChatFormatting.GREEN + " - The Naruto mod is up to date.";
                    updatestatus = "updated";
                    NarutoMod.logger.info("Current copy is up to date.");
                }
            } else if (mcVersion <= NarutoMod.mcVersion && modversion <= NarutoMod.modVersion) {
                updatetext = EnumChatFormatting.AQUA + " - This is a pre release!";
                updatestatus = "updated";
                NarutoMod.logger.info("Current copy is a pre-release.");
            } else {
                updatetext = EnumChatFormatting.GOLD + " - An update is available!";
                updatestatus = "update";
                NarutoMod.logger.info("Update found.");
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
            updatetext = EnumChatFormatting.RED + " - Error reading update file :(";
        } catch (IOException e) {
            e.printStackTrace();
            updatetext = EnumChatFormatting.RED + " - Could not connect to the update info file :(";
        }

        try {
            URL urlServer = new URL("http://www.sekwah.com/naruto-mod/OfficialServerInfo.txt");
            URLConnection connection = urlServer.openConnection();
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

    private String readJSONFileStream(InputStream filestreamJson) throws IOException {
        InputStreamReader inr = new InputStreamReader(filestreamJson, "ASCII");
        BufferedReader br = new BufferedReader(inr);
        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
            sb.append("\n");
        }

        br.close();
        inr.close();

        return sb.toString();
    }
}
