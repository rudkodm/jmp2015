package com.rm.eholiday.xml;

import java.io.IOException;
import java.io.Writer;

class UnicodeUnpairedSurrogateRemover extends CodePointTranslator {

    @Override
    public boolean translate(int codepoint, Writer out) throws IOException {
        return codepoint >= Character.MIN_SURROGATE && codepoint <= Character.MAX_SURROGATE;
    }

}
