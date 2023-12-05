package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    private static final API instance = new API();

    private String privateApiKey = "64be8b8e-46cd-45dc-b27e-7fb0a3fcb8d1";
    private URL requestURL = null;

    public JsonNode getPlayerStatsFromName(String name){
        return getPlayerStatsFromUUID(getUUID(name));
    }

    public String getUUID(String name) {
        try {
            requestURL = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
            HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() != 200) return null;
            return getJSONResponse(getRequestResponse(con.getInputStream())).get("id").asText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getPlayerStatsFromUUID(String uuid) {
        try {
            requestURL = new URL("https://api.hypixel.net/player?key=" + privateApiKey + "&uuid=" + uuid);
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
            requestURL = new URL("https://api.hypixel.net/status?key=" + privateApiKey + "&uuid=" + uuid);
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
            requestURL = new URL("https://minotar.net/armor/body/" + playerName + "/150.png");
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
                BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

                return bufferedImage;
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
