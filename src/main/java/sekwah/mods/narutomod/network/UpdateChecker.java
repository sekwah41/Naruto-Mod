package sekwah.mods.narutomod.network;

import net.minecraft.util.EnumChatFormatting;
import sekwah.mods.narutomod.NarutoMod;
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
 */
public class UpdateChecker {

    public static String updatestatus = "checking";
    public static String updatetext = EnumChatFormatting.YELLOW + " - Checking for update...";
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
    }

    public void startUpdateChecker() {

            this.threadTrigger.schedule(new TimerTask() {

                public void run() {
                    NarutoMod.LOGGER.info("Checking for updates");
                    checkUpdate(updateURL);
                }
            }, 0L); // Shorter than 15 mins by 10 seconds just to make sure its not on the border and online

            // players are missed

    }

    public void checkUpdate(URL url) {
        try {
            InputStream in = url.openStream();
            String json = readJSONFileStream(in);
            JSONObject updateFile = new JSONObject(json);
            int mcversion = updateFile.getInt("mcversion");
            int modversion = updateFile.getInt("modversion");
            String newestDownload = updateFile.getString("updateLink");

            if (mcversion == NarutoMod.mcversion && modversion == NarutoMod.modversion) {
                if (NarutoMod.isPreRelease) {
                    updatetext = EnumChatFormatting.YELLOW + " - The official release is now available!";
                    updatestatus = "updated";
                    NarutoMod.LOGGER.info("Current copy is up to date.");
                } else {
                    updatetext = EnumChatFormatting.GREEN + " - The Naruto mod is up to date.";
                    updatestatus = "updated";
                    NarutoMod.LOGGER.info("Current copy is up to date.");
                }
            } else if (mcversion <= NarutoMod.mcversion && modversion <= NarutoMod.modversion) {
                updatetext = EnumChatFormatting.AQUA + " - This is a pre release!";
                updatestatus = "updated";
                NarutoMod.LOGGER.info("Current copy is a pre-release.");
            } else {
                updatetext = EnumChatFormatting.GOLD + " - An update is available!";
                updatestatus = "update";
                NarutoMod.LOGGER.info("Update found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
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
