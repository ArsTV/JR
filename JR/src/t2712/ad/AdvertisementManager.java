package t2712.ad;

import java.util.List;

import t2712.ConsoleHelper;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private long amountPerOneDisplaying;
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){
        List<Advertisement> availableVideos = storage.list();
        if (availableVideos.isEmpty()) throw new NoVideoAvailableException();

        //ConsoleHelper.writeMessage("calling processVideos method");
    }
}