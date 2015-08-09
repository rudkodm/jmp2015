package com.rm.eholiday.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

abstract class CharSequenceTranslator {

    public abstract int translate(CharSequence input, int index, Writer out) throws IOException;

    public final String translate(final CharSequence input) {
        if (input == null) {
            return null;
        }
        try {
            final StringWriter writer = new StringWriter(input.length() * 2);
            translate(input, writer);
            return writer.toString();
        } catch (final IOException ioe) {
            // this should never ever happen while writing to a StringWriter
            throw new RuntimeException(ioe);
        }
    }

    public final void translate(final CharSequence input, final Writer out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (input == null) {
            return;
        }
        int pos = 0;
        final int len = input.length();
        while (pos < len) {
            final int consumed = translate(input, pos, out);
            if (consumed == 0) {
                final char[] c = Character.toChars(Character.codePointAt(input, pos));
                out.write(c);
                pos+= c.length;
                continue;
            }
            // contract with translators is that they have to understand codepoints
            // and they just took care of a surrogate pair
            for (int pt = 0; pt < consumed; pt++) {
                pos += Character.charCount(Character.codePointAt(input, pos));
            }
        }
    }

}
