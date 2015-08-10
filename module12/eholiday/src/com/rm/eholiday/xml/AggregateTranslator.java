package com.rm.eholiday.xml;

import java.io.IOException;
import java.io.Writer;

class AggregateTranslator extends CharSequenceTranslator {

    private final CharSequenceTranslator[] translators;

    public static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    public AggregateTranslator(final CharSequenceTranslator... translators) {
        this.translators = clone(translators);
    }

    @Override
    public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
        for (final CharSequenceTranslator translator : translators) {
            final int consumed = translator.translate(input, index, out);
            if(consumed != 0) {
                return consumed;
            }
        }
        return 0;
    }

}
