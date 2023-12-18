package fr.mimifan.projethypixel.manager;

import fr.mimifan.projethypixel.api.data.skyblock.SkyblockInfos;

import java.util.HashMap;

/**
 * Class SkyBlockProfileManager
 * Handles the association between a skyblock profile's stats and its unique identifier.
 */
public class SkyBlockProfileManager {

    /**
     * The current instance of this class.
     */
    private static final SkyBlockProfileManager instance = new SkyBlockProfileManager();

    /**
     * The association map of profiles identifiers and their infos.
     */
    private final HashMap<String, SkyblockInfos> profiles = new HashMap<>();

    /**
     * Creates the association between the given profile and its unique identifier.
     * @param profile the profile to associate with its unique identifier.
     */
    public void addProfile(SkyblockInfos profile) {
        profiles.put(profile.getProfileId(), profile);
    }

    /**
     * Gets the profile associated with the given profile ID.
     * @param profileId the profile ID to find its association.
     * @return the profile associated to the given profile ID.
     */
    public SkyblockInfos getProfile(String profileId) {
        return profiles.getOrDefault(profileId, null);
    }

    /**
     * @return {@link SkyBlockProfileManager#instance}
     */
    public static SkyBlockProfileManager getInstance() {
        return instance;
    }
}
