package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mimifan.projethypixel.frames.SettingKeyFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.prefs.Preferences;

/**
 * This class handles all requests to API <br>
 * It also retrieves the current API key and last modification time.
 */
public class API {

    private static final API instance = new API();

    /**
     * Retrieves the current API key from Preferences for this class. <br>
     * It makes it so the key stays saved between two application's executions.
     * @return Current API key.
     */
    public String getAPIKey() {
        return Preferences.userNodeForPackage(API.class).get("papikey", "64be8b8e-46cd-45dc-b27e-7fb0a3fcb8d1");
    }

    /**
     * Saves a new API key in Preferences for this class. <br>
     * Also saves the last modification time (now) in miliseconds. <br>
     * So user will be asked to change it again 3 days after. (duration of a hypixel API key)
     * @param value The new API key to set.
     */
    public void saveAPIKey(String value) {
        Preferences prefs = Preferences.userNodeForPackage(API.class);
        prefs.put("papikey", value);
        prefs.putLong("papikeyLastEdit", System.currentTimeMillis());
    }

    /**
     * Retrieves the last time (in milliseconds) the API key was updated.
     * @return The last time the API key was updated.
     */
    public long getLastEdit() {
        return Preferences.userNodeForPackage(API.class).getLong("papikeyLastEdit", System.currentTimeMillis() - 4 * 24 * 60 * 60 * 1000);
    }

    /**
     * Opens the dialog to update the API key.
     */
    public void changeAPIKey() {
        SwingUtilities.invokeLater(SettingKeyFrame::new);
    }

    /**
     * Gets a player's stats from hypixel API via player's name which will be converted to it's UUID.
     * @param name The name of the player to retrieve stats from.
     * @return The stats of the player
     */
    public JsonNode getPlayerStatsFromName(String name){
        return getPlayerStatsFromUUID(getUUID(name));
    }

    /**
     * Gets a player's unique ID from its username.
     * @param name the name of the player to get its unique ID from.
     * @return the unique ID of the player.
     */
    public String getUUID(String name) {
        try {
            URL requestURL = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            return getJSONResponse(getRequestResponse(con.getInputStream())).get("id").asText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the username of the player having the given unique ID.
     * @param uuid The unique ID of the player to get the username for.
     * @return The username of the player.
     */
    public String getName(String uuid) {
        try {
            URL requestURL = new URL("https://playerdb.co/api/player/minecraft/"+uuid);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            return getJSONResponse(getRequestResponse(con.getInputStream())).get("data").get("player").get("username").asText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a player's hypixel stats via its unique ID.
     * @param uuid The unique ID of the player.
     * @return The player's hypixel stats as a JsonNode.
     */
    public JsonNode getPlayerStatsFromUUID(String uuid) {
        try {
            URL requestURL = new URL("https://api.hypixel.net/player?key=" + getAPIKey() + "&uuid=" + uuid);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println("Retrieved data from " + requestURL);
            return getJSONResponse(getRequestResponse(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the information about a skyblock profile from its ID.
     * @param id the ID of the profile to retrieve information from.
     * @return the information about the profile as a JsonNode.
     */
    public JsonNode getSkyblockProfileInfos(String id) {
        try {
            URL requestURL = new URL("https://api.hypixel.net/skyblock/profile?key=" + getAPIKey() + "&profile=" + id);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println("Retrieved data from " + requestURL);
            return getJSONResponse(getRequestResponse(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the resources associated to Skyblock's skills requirements.
     * @return The response corresponding to all information about Skyblock's skills requirements. (skillName, level, totalExp required...)
     */
    public JsonNode getSkillsLeveling() {
        try {
            URL requestURL = new URL("https://api.hypixel.net/resources/skyblock/skills");
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println("Retrieved data from " + requestURL);
            return getJSONResponse(getRequestResponse(con.getInputStream())).get("skills");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the reponse of a HttpURLConnection request as a String.
     * @param stream the input stream to read from.
     * @return the response as a String.
     */
    public String getRequestResponse(InputStream stream){
        StringBuilder content;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            content = new StringBuilder();

            String inputLine;
            while ((inputLine = in.readLine()) != null) content.append(inputLine);
            in.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        return content.toString();
    }

    /**
     * Parses the String response of a HttpURLConnection request to a JsonNode object.
     * @param responseString the response (as a String) to parse.
     * @return the response as a JsonNode.
     */
    public JsonNode getJSONResponse(String responseString){
        ObjectMapper objectMapper = new ObjectMapper();
        try { return objectMapper.readTree(responseString); }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    /**
     * Returns the information about a player's online status. <br>
     * Note that if the player is online, we can also retrieve the location in the server where he is.
     * @param uuid the player's unique ID.
     * @return the information about the player as a JSonNode.
     */
    public JsonNode getSession(String uuid){
        try {
            URL requestURL = new URL("https://api.hypixel.net/status?key=" + getAPIKey() + "&uuid=" + uuid);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            StringBuilder content = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) content.append(inputLine);
            in.close();

            return (new ObjectMapper()).readTree(content.toString()).get("session");
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    public JLabel getSessionLabel(String playerName) {
        JsonNode session = getSession(getUUID(playerName));
        if(session.get("online").asBoolean()) return new JLabel("<html><font color=#55FF55>" + playerName + "</font></html>");
        else return new JLabel("<html><font color=#FF5555>" + playerName + "</font></html>");
    }

    /**
     * Gets a player's skin as a BufferedImage from <a href="https://minotar.net">Minotar</a> <br>
     * Minotar is a really nice website to visualize or get a player's skin, helmet, head and more.
     * @param playerName the name of the player to get the skin of.
     * @return a BufferedImage that represents the skin of the player.
     */
    public BufferedImage getSkin(String playerName) {
        try {
            URL requestURL = new URL("https://minotar.net/armor/body/" + playerName + "/150.png");
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;

            try (InputStream inputStream = con.getInputStream()) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

                return ImageIO.read(byteArrayInputStream);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static API getInstance() {
        return instance;
    }
}
