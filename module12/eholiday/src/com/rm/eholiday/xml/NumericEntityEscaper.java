package com.rm.eholiday.xml;

import java.io.IOException;
import java.io.Writer;

class NumericEntityEscaper extends CodePointTranslator {

    private final int below;
    private final int above;
    private final boolean between;

    private NumericEntityEscaper(final int below, final int above, final boolean between) {
        this.below = below;
        this.above = above;
        this.between = between;
    }

    public static NumericEntityEscaper between(final int codepointLow, final int codepointHigh) {
        return new NumericEntityEscaper(codepointLow, codepointHigh, true);
    }

    @Override
    public boolean translate(final int codepoint, final Writer out) throws IOException {
        if(between) {
            if (codepoint < below || codepoint > above) {
                return false;
            }
        } else {
            if (codepoint >= below && codepoint <= above) {
                return false;
            }
        }

        out.write("&#");
        out.write(Integer.toString(codepoint, 10));
        out.write(';');
        return true;
    }

}
