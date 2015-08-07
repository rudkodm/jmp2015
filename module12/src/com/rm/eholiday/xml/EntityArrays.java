package com.rm.eholiday.xml;

class EntityArrays {

    public static String[][] BASIC_ESCAPE() { return BASIC_ESCAPE.clone(); }
    private static final String[][] BASIC_ESCAPE = {
            {"\"", "&quot;"}, // " - double-quote
            {"&", "&amp;"},   // & - ampersand
            {"<", "&lt;"},    // < - less-than
            {">", "&gt;"},    // > - greater-than
    };


    public static String[][] APOS_ESCAPE() { return APOS_ESCAPE.clone(); }
    private static final String[][] APOS_ESCAPE = {
            {"'", "&apos;"}, // XML apostrophe
    };

}
