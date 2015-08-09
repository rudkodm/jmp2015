package com.rm.eholiday.http;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;
import com.rm.eholiday.*;
import com.rm.eholiday.config.Config;

class Processor {

    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String ENCODING_GZIP = "gzip";
    private static final String ENCODING_DEFLATE = "deflate";

    private static final int SOCKET_CONNECT_TIMEOUT = Config.getHttp().getSocketConnectTimeout();
    private static final int SOCKET_READ_TIMEOUT = Config.getHttp().getSocketReadTimeout();
    private static final String USER_AGENT = Config.getHttp().getUserAgent();
    private static final boolean FOLLOW_REDIRECTS = Config.getHttp().getFollowRedirects();

    private static final InputHandler DUMMY_HANDLER = new InputHandler() {
        @Override
        public void handleInput(InputStream input) throws IOException {
            while (input.read() != -1);
        }
    };

    private static final Log log = Log.newLog("HttpProc");

    private String requestMethod = "GET";
    private String urlStr;
    private Map<String, String> requestHeaders = new HashMap<String, String>();
    private String requestBody;
    private int expectedResponseCode = HttpURLConnection.HTTP_OK;
    private Map<String,List<String>> responseHeaders;
    private InputHandler inputHandler = DUMMY_HANDLER;
    private InputHandler errorHandler = DUMMY_HANDLER;

    public Processor() {
        requestHeaders.put(HEADER_USER_AGENT, USER_AGENT);
        requestHeaders.put(HEADER_ACCEPT_ENCODING, ENCODING_GZIP + ", " + ENCODING_DEFLATE);
    }

    public Processor(InputHandler inputHandler) {
        this();
        this.inputHandler = inputHandler;
    }

    public Processor(String urlStr, InputHandler inputHandler) {
        this();
        this.urlStr = urlStr;
        this.inputHandler = inputHandler;
    }

    public Processor(String urlStr, int expectedResponseCode) {
        this();
        this.urlStr = urlStr;
        this.expectedResponseCode = expectedResponseCode;
    }

    public void process() {
        log.debug("Start processing. URL: " + urlStr);

        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            String message = "Malformed URL: " + urlStr;
            log.error(message, e);
            throw new IllegalStateException(message, e);
        }

        while (true) {
            HttpURLConnection connection;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                log.error("Unable to connect to " + urlStr, e);
                continue;
            }

            connection.setConnectTimeout(SOCKET_CONNECT_TIMEOUT);
            connection.setReadTimeout(SOCKET_READ_TIMEOUT);
            connection.setInstanceFollowRedirects(FOLLOW_REDIRECTS);

            if (requestBody != null && !requestBody.isEmpty()) {
                connection.setDoOutput(true);
            }

            try {
                connection.setRequestMethod(requestMethod);
            } catch (ProtocolException e) {
                String msg = "ProtocolException occurred when setting method: " + requestMethod;
                log.error(msg, e);
                throw new IllegalStateException(msg, e);
            }

            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            if (requestBody != null && !requestBody.isEmpty()) {
                OutputStream output;
                try {
                    output = connection.getOutputStream();
                } catch (IOException e) {
                    log.error("Unable to get output stream", e);
                    continue;
                }

                OutputStreamWriter writer = new OutputStreamWriter(output);
                try {
                    writer.write(requestBody);
                } catch (IOException e) {
                    log.error("Unable to write request body", e);
                    continue;
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        log.error("Unable to close writer", e);
                    }
                }
            }

            int responseCode;
            try {
                responseCode = connection.getResponseCode();
            } catch (IOException e) {
                log.error("Unable to get response code", e);
                continue;
            }

            if (responseCode != expectedResponseCode) {
                String msg = "Unexpected HTTP response code: " + responseCode;
                log.error(msg);
                throw new IllegalStateException(msg);
            }

            responseHeaders = connection.getHeaderFields();

            InputStream input;
            try {
                input = connection.getInputStream();
            } catch (IOException e) {
                log.error("Unable to get input stream", e);
                continue;
            }

            List<String> encList = responseHeaders.get(HEADER_CONTENT_ENCODING);
            if (encList != null && encList.size() > 0) {
                String encoding = encList.get(0);
                if (ENCODING_GZIP.equals(encoding)) {
                    try {
                        input = new GZIPInputStream(input);
                    } catch (IOException e) {
                        String msg = "Error occurred while wrapping input with GZIP decoder";
                        log.error(msg, e);
                        continue;
                    }
                } else if (ENCODING_DEFLATE.equals(encoding)) {
                    input = new DeflaterInputStream(input);
                } else {
                    String msg = "Unknown content encoding: " + encoding;
                    log.error(msg);
                    throw new IllegalStateException(msg);
                }
            }

            try {
                log.debug("Start handling input. URL: " + urlStr);
                inputHandler.handleInput(input);
                log.debug("Handling input completed. URL: " + urlStr);
            } catch (IOException e) {
                log.error("Unable to read from input stream", e);
                InputStream error = connection.getErrorStream();
                if (error != null) {
                    try {
                        log.debug("Start handling error. URL: " + urlStr);
                        errorHandler.handleInput(error);
                        log.debug("Handling error completed. URL: " + urlStr);
                    } catch (IOException e1) {
                        log.error("Unable to read from error stream", e1);
                    } finally {
                        try {
                            error.close();
                        } catch (IOException e1) {
                            log.error("Unable to close error stream", e1);
                        }
                    }
                }
                continue;
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("Unable to close input stream", e);
                }
            }

            break;
        }

        log.debug("Processing completed. URL: " + urlStr);
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return urlStr;
    }

    public void setUrl(String url) {
        this.urlStr = url;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public int getExpectedResponseCode() {
        return expectedResponseCode;
    }

    public void setExpectedResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public InputHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(InputHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

}
