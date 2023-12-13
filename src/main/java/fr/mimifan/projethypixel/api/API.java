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

public class API {

    private static final API instance = new API();

    public String getAPIKey() {
        return Preferences.userNodeForPackage(API.class).get("papikey", "64be8b8e-46cd-45dc-b27e-7fb0a3fcb8d1");
    }
    public void saveAPIKey(String value) {
        Preferences prefs = Preferences.userNodeForPackage(API.class);
        prefs.put("papikey", value);
        prefs.putLong("papikeyLastEdit", System.currentTimeMillis());
    }
    public long getLastEdit() {
        return Preferences.userNodeForPackage(API.class).getLong("papikeyLastEdit", System.currentTimeMillis() - 4 * 24 * 60 * 60 * 1000);
    }
    public void changeAPIKey() {
        SwingUtilities.invokeLater(SettingKeyFrame::new);
    }

    public JsonNode getPlayerStatsFromName(String name){
        return getPlayerStatsFromUUID(getUUID(name));
    }

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

    public JsonNode getPlayerStatsFromUUID(String uuid) {
        try {
            URL requestURL = new URL("https://api.hypixel.net/player?key=" + getAPIKey() + "&uuid=" + uuid);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println(con.getResponseCode());
            return getJSONResponse(getRequestResponse(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getSkyblockProfileInfos(String id) {
        try {
            URL requestURL = new URL("https://api.hypixel.net/skyblock/profile?key=" + getAPIKey() + "&profile=" + id);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println(con.getResponseCode());
            return getJSONResponse(getRequestResponse(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getSkillsLeveling() {
        try {
            URL requestURL = new URL("https://api.hypixel.net/resources/skyblock/skills");
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            System.out.println(con.getResponseCode());
            return getJSONResponse(getRequestResponse(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRequestResponse(InputStream stream){
        //Ne pas initialiser content ici, si la réponse n'est pas lisible on renverrai un StringBuilder vide
        //Et ce serait plus compliqué à comprendre d'où vient l'erreur.
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

    public JsonNode getJSONResponse(String responseString){
        ObjectMapper objectMapper = new ObjectMapper();
        try { return objectMapper.readTree(responseString); }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

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
