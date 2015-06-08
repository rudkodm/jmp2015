package by.rudko.concurrency.tasks;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by rudkodm on 6/8/15.
 */
public interface FileProcessingTask {
    void execute(BlockingQueue<File> filesQueue, Object context) throws InterruptedException;
}
