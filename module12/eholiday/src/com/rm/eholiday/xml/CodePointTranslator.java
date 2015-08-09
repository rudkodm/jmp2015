package com.rm.eholiday.xml;

import java.io.IOException;
import java.io.Writer;

abstract class CodePointTranslator extends CharSequenceTranslator {

    @Override
    public final int translate(final CharSequence input, final int index, final Writer out) throws IOException {
        final int codepoint = Character.codePointAt(input, index);
        final boolean consumed = translate(codepoint, out);
        return consumed ? 1 : 0;
    }

    public abstract boolean translate(int codepoint, Writer out) throws IOException;

}
