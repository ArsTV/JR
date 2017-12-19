package t2712.ad;

/**
 * Created by DELL on 12/19/2017.
 */
public class AdvertisementStorage {
    private static AdvertisementStorage advertisementStorage;

    private AdvertisementStorage() {
    }

    public AdvertisementStorage getInstance() {
        return advertisementStorage;
    }
}