package by.rudko.gc;

public class Bean {
    private static int counter = 0;

    @SuppressWarnings("unused")
    private byte[] size = new byte[1000];

    private int id = 0;

    public Bean(int size) {
        this();
        this.size = new byte[size];
    }

    public int getId() {
        return id;
    }

    public Bean() {
        this.id = counter++;
    }
}
