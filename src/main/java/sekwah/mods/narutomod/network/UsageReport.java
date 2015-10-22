package sekwah.mods.narutomod.network;

import com.google.common.collect.Maps;
import net.minecraft.server.MinecraftServer;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.settings.NarutoSettings;

import java.io.*;
import java.net.*;
import java.util.*;

public class UsageReport {

    private final Timer threadTrigger = new Timer("UsageReport Timer", true);

    private final URL reportURL;

    private final String uniqueID = UUID.randomUUID().toString();

    private final Map reportData = Maps.newHashMap(); // general data from mod, may include more or less

    private final boolean isClient;

    private boolean optOut = false;

    private final Object syncLock = new Object();

    private int reportCounter = 0;

    // Once this is complete there will be a lot more data complete
    public UsageReport(boolean isClient) {
        this.isClient = isClient;
        try {
            this.reportURL = new URL("http://report.sekwah.com/naruto-mod/");
        } catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }
    }

    public void startUsageReport() {
        if(NarutoSettings.usageReportMod == 0 || NarutoSettings.usageReportMod == 1){
            addBase();
            if(NarutoSettings.usageReportMod == 0){
                addStats();
            }

            this.threadTrigger.schedule(new TimerTask() {

                public void run() {

                    HashMap hashmap;
                    synchronized (UsageReport.this.syncLock) {

                        if(NarutoSettings.usageReportMod == 0){
                            addMemoryStats();
                        }

                        addData("reportCounter", ++reportCounter); // Will show how long the mod has been running as
                        // each update is 14 mins 50 seconds(to make sure its 100% inside the refresh time and doesnt
                        // count a player as offline when they are on, the only time it will is if they have no
                        // connection or have opted out of using this. The report counter will be mostly used to
                        // track how memory is being used over time to see how the mod is performing.

                        // some code may not actually be needed but when changing the map it seems best to syncronize
                        // it at all times to make sure the method isnt being run twice at once and 50 50 of each data
                    }

                    NarutoMod.LOGGER.info("Sending UsageReport data");
                    NarutoMod.LOGGER.info(sendData(reportURL, buildPostString(reportData)));
                }
            }, 0L, 890000L); // Shorter than 15 mins by 10 seconds just to make sure its not on the border and online
            // players are missed
        }

    }

    public void addData(String dataName, Object data) {
        synchronized (this.syncLock) {
            this.reportData.put(dataName, data);
        }
    }

    // Not used atm but may be needed
    public void removeData(String dataName) {
        synchronized (this.syncLock) {
            this.reportData.remove(dataName);
        }
    }

    public void addBase(){
        this.addData("uuid", uniqueID);// not the players uuid but a randomly generated one, more like a session token
        this.addData("version", NarutoMod.version);
        this.addData("isClient", isClient);
    }

    public void addStats() {
        // general system stats
        this.addData("os_name", System.getProperty("os.name"));
        this.addData("os_version", System.getProperty("os.version"));
        this.addData("os_architecture", System.getProperty("os.arch"));
        this.addData("java_version", System.getProperty("java.version"));
    }

    public void addMemoryStats() {
        this.addData("memory_total", Runtime.getRuntime().totalMemory());
        this.addData("memory_max", Runtime.getRuntime().maxMemory());
        this.addData("memory_free", Runtime.getRuntime().freeMemory());
        this.addData("cpu_cores", Runtime.getRuntime().availableProcessors());
    }

    // Will contain stuff like fireballs shot and all sorts just for fun o3o
    public void addOtherData() {

    }

    public String buildPostString(Map map){
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)iterator.next();
            // adds an & before adding data so like version=0.3.1&java_version=1.7 but wont be added at the start
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append('&');
            }

            // gets the key for the value of the map as each part of the data will be key=value&key=value and so on
            try
            {
                stringbuilder.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                NarutoMod.LOGGER.error("Unsupported Encoding in report data");
                e.printStackTrace();
            }

            if (entry.getValue() != null)
            {
                stringbuilder.append('=');

                try
                {
                    stringbuilder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                }
                catch (UnsupportedEncodingException e)
                {
                    NarutoMod.LOGGER.error("Unsupported Encoding in report data");
                    e.printStackTrace();
                }
            }
        }

        return stringbuilder.toString();
    }

    public String sendData(URL url, String dataString) {
        try {
            Proxy proxy = MinecraftServer.getServer() == null ? null : MinecraftServer.getServer().getServerProxy();

            if (proxy == null)
            {
                proxy = Proxy.NO_PROXY;
            }

            HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpurlconnection.setRequestProperty("Content-Length", "" + dataString.getBytes().length);
            httpurlconnection.setRequestProperty("Content-Language", "en-US");
            httpurlconnection.setUseCaches(false);
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);
            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
            dataoutputstream.writeBytes(dataString);
            dataoutputstream.flush();
            dataoutputstream.close();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
            StringBuffer stringbuffer = new StringBuffer();
            String s1;

            while ((s1 = bufferedreader.readLine()) != null) {
                stringbuffer.append(s1);
                stringbuffer.append('\r');
            }

            bufferedreader.close();
            return stringbuffer.toString();
        } catch (Exception e) {
            NarutoMod.LOGGER.error("Error sending the usage report.");
            e.printStackTrace();
            return "";
        }
    }



}
