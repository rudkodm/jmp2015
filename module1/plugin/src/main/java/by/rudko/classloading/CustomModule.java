package by.rudko.classloading;

public class CustomModule implements Module{

    @Override
    public void load() {
        System.out.println(">> Load CustomModule");
    }

    @Override
    public void run(){
        System.out.println(">> Run CustomModule");
    }

    @Override
    public void unload() {
        System.out.println(">> Unload CustomModule");
    }
}
