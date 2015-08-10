package com.rm.eholiday.config;

public class HttpConfig extends PropertiesConfig {

    private int socketConnectTimeout;
    private int socketReadTimeout;
    private String userAgent;
    private boolean followRedirects;

    HttpConfig() {
        super("http.properties");
        initialize(HttpConfig.class);
    }

    public int getSocketConnectTimeout() {
        return socketConnectTimeout;
    }

    public int getSocketReadTimeout() {
        return socketReadTimeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public boolean getFollowRedirects() {
        return followRedirects;
    }

}
