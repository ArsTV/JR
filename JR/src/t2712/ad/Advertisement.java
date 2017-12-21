package t2712.ad;

/**
 * Created by DELL on 12/19/2017.
 */

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = this.initialAmount / this.hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getHits() {
        return hits;
    }

    public void revalidate() {
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        }
        hits--;
    }

    @Override
    public String toString() {
        return name + " is displaying... " +
                getAmountPerOneDisplaying() +
                ", " +
                getAmountPerOneDisplaying() / duration * 1000;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }
}
