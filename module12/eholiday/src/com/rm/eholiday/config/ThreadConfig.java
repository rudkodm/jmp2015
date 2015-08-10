package com.rm.eholiday.config;

public class ThreadConfig extends PropertiesConfig {

    private int poolSize;
    private int queueSize;
    private long pollingTimeout;
    private int notificationTimeout;

    ThreadConfig() {
        super("thread.properties");
        initialize(ThreadConfig.class);
    }

    public int getPoolSize() {
        return poolSize;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public long getPollingTimeout() {
        return pollingTimeout;
    }

    public int getNotificationTimeout() {
        return notificationTimeout;
    }

}
