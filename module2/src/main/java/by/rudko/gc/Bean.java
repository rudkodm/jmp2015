package by.rudko.gc;

public class Bean {
    private static int counter = 0;

    @SuppressWarnings("unused")
    private byte[] size = new byte[100];

    private int id = 0;

    public int getId() {
        return id;
    }

    public Bean() {
        this.id = counter++;
    }
}
