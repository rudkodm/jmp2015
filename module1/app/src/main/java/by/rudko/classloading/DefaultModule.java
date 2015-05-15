package by.rudko.classloading;

public class DefaultModule implements Module{
    @Override
    public void load() {
        System.out.println(">> Load DefaultModule");
    }

    @Override
    public void run() {
        System.out.println(">> Run DefaultModule");
    }

    @Override
    public void unload() {
        System.out.println(">> Unload DefaultModule");
    }
}
