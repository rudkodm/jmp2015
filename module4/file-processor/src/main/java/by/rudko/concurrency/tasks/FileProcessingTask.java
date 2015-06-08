package by.rudko.concurrency.tasks;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by rudkodm on 6/8/15.
 */
public interface FileProcessingTask {
    void execute(BlockingQueue<File> filesQueue, Map<?,?> context) throws InterruptedException;
}
