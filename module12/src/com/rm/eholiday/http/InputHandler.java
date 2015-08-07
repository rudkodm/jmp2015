package com.rm.eholiday.http;

import java.io.IOException;
import java.io.InputStream;

interface InputHandler {

    public void handleInput(InputStream input) throws IOException;

}
