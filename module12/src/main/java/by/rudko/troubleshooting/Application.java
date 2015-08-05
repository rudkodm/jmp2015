package by.rudko.troubleshooting;

import java.util.concurrent.TimeUnit;

public class Application {
  
    
    public static void main(String[] args) throws Exception {
        
        final Object resource1 = new Object();
        final Object resource2 = new Object(); 
        
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1 lock resource 1");
                doWork();
                System.out.println("Thread 1 have done some work");
                synchronized (resource2){System.out.println("Thread 1 lock resource 2");}
            }
        }).start();
        
        
        
        
        new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2 lock resource 2");
                doWork();
                System.out.println("Thread 2 have done some work");
                synchronized (resource1){System.out.println("Thread 2 lock resource 1");}
            }
        }).start();
        
    }

    
    private static void doWork() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (Exception e) {
            // DO NOTHING
        }
    }
    
}
