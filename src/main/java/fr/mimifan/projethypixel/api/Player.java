package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

public class Player {

    private String username;
    private String uuid;

    private Date firstJoin, lastLogin;
    private JsonNode infos;
    private boolean online;

    private Bedwars bedwarsInfos;


    public Player(JsonNode infos, boolean online) {
        this.username = infos.get("displayname").asText();
        this.uuid = infos.get("uuid").asText();
        this.infos = infos;
        this.online = online;

        this.firstJoin = new Date( infos.get("firstLogin").asLong() );
        this.lastLogin = new Date( infos.get("lastLogin").asLong() );
        System.out.println(online);

        init();
    }

    private void init() {
        this.bedwarsInfos = new Bedwars(infos.get("stats").get("Bedwars"));
    }


    public String getName() {
        return username;
    }

    public String getUUID() {
        return uuid;
    }

    public Date getFirstJoin() {
        return firstJoin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public boolean isOnline() {
        return online;
    }

    public Bedwars getBedwarsInfos() {
        return bedwarsInfos;
    }
}
