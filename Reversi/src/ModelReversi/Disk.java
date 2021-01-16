package ModelReversi;

public class Disk {
    int color;
    boolean validLocate;
    boolean lastDisk = false;

    public Disk(int color) {
        this.color = color;
        validLocate = false;
    }

    public int getColor() {
        return color;
    }

    public boolean isLastDisk() {
        return lastDisk;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setValidLocate(boolean validLocate) {
        this.validLocate = validLocate;
    }

    public void setLastDisk(boolean lastDisk) {
        this.lastDisk = lastDisk;
    }

    @Override
    public String toString() {
        if (color == 0)
            return ('\u26AB') + "\u2009";
        else if (color == 1)
            return ('\u26AA') + "\u2009";
        else
            return ('\u22C5') + " ";
    }
}
