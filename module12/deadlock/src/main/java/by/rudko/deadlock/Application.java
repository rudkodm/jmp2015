package by.rudko.deadlock;


import java.text.MessageFormat;

public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }



    private final Object resource1 = "Resource 1";
    private final Object resource2 = "Resource 2";
    private final Object resource3 = "Resource 3";
    private final Object resource4 = "Resource 4";

    private void run(String[] args) {
        runProcess(resource1, resource2);
        runProcess(resource2, resource3);
        runProcess(resource3, resource4);
        runProcess(resource4, resource1);
    }

    private void runProcess(Object resource1, Object resource2) {
        new Thread(()->{
            log("Process started");
            synchronized (resource1){
                log("Loc resource: {0}", resource1);
                doJob();
                synchronized (resource2){
                    log("Loc resource: {0}", resource2);
                };
            }
            log("Process finished");
        }).start();
    }

    private void doJob() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // DO NOTHING
        }
    }


    private static String log(String messagePattern, Object... args){
        String threadName = Thread.currentThread().getName();
        String logPattern = MessageFormat.format("{0} : {1}", threadName, messagePattern);
        String logMessage = MessageFormat.format(logPattern, args);
        System.out.println(logMessage);
        return logMessage;
    }
}
