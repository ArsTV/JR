package t2712.ad;

import t2712.ConsoleHelper;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private long amountPerOneDisplaying;
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
