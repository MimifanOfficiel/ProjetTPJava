package fr.mimifan.projethypixel.manager;

import fr.mimifan.projethypixel.api.data.skyblock.SkyblockInfos;

import java.util.HashMap;

public class SkyBlockProfileManager {

    private static final SkyBlockProfileManager instance = new SkyBlockProfileManager();

    private final HashMap<String, SkyblockInfos> profiles = new HashMap<>();


    public void addProfile(SkyblockInfos profile) {
        profiles.put(profile.getProfileId(), profile);
    }


    public SkyblockInfos getProfile(String profileId) {
        return profiles.getOrDefault(profileId, null);
    }


    public static SkyBlockProfileManager getInstance() {
        return instance;
    }
}
