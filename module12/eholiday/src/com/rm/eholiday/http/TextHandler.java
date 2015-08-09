package com.rm.eholiday.http;

import java.io.*;

class TextHandler implements InputHandler {

    private String text;

    @Override
    public void handleInput(InputStream input) throws IOException {
        text = null;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
        StringBuilder textBuilder = new StringBuilder();

        String line;
        while ((line = inputReader.readLine()) != null) {
            textBuilder.append(line).append('\n');
        }

        text = textBuilder.toString();
    }

    public String getText() {
        return text;
    }

}
