package com.rm.eholiday.http;

import java.util.*;

class HtmlStringUtil {

    private static final String BEGIN_DL = "<dl";
    private static final String END_DL = "</dl>";
    private static final String BEGIN_DT = "<dt";
    private static final String END_DT = "</dt>";
    private static final String BEGIN_DD = "<dd";
    private static final String END_DD = "</dd>";

    public static List<String> search(String source, String begin, String end, boolean inclusive, int maxCount) {
        begin = begin == null ? "" : begin;
        end = end == null ? "" : end;
        List<String> searchList = new ArrayList<String>();

        if (source != null) {
            int index = 0;
            while ((index = source.indexOf(begin, index)) >= 0 && (searchList.size() < maxCount || maxCount == 0)) {
                int startIndex = index + begin.length();
                int endIndex = end.isEmpty() ? source.length() : source.indexOf(end, startIndex);

                if (endIndex < 0) {
                    break;
                }

                if (inclusive) {
                    startIndex = index;
                    endIndex += end.length();
                }

                searchList.add(source.substring(startIndex, endIndex));
                index = endIndex + end.length();
            }
        }

        return searchList;
    }

    public static String searchFirst(String source, String begin, String end, boolean inclusive) {
        List<String> searchList = search(source, begin, end, inclusive, 1);
        return searchList.size() > 0 ? searchList.get(0) : null;
    }

    public static List<String> searchAll(String source, String begin, String end, boolean inclusive) {
        return search(source, begin, end, inclusive, 0);
    }

    public static String finalize(String str) {
        return str.trim();
    }

    public static List<String> finalize(List<String> strList) {
        for (int i = 0; i < strList.size(); i++) {
            strList.set(i, finalize(strList.get(i)));
        }
        return strList;
    }

    public static boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static List<Map<String, List<String>>> searchAndParseDlTags(String source, int maxCount) {
        List<String> dlTags = search(source, BEGIN_DL, END_DL, true, maxCount);
        List<Map<String, List<String>>> parsedDlTags = new ArrayList<Map<String, List<String>>>(dlTags.size());

        for (String dlTag : dlTags) {
            List<String> tagPairs = searchAll(dlTag, BEGIN_DT, END_DD, false);
            Map<String, List<String>> parsedDlTag = new HashMap<String, List<String>>(tagPairs.size());

            for (String tagPair : tagPairs) {
                String dt = searchFirst(tagPair, ">", END_DT, false).trim();
                String dd = searchFirst(tagPair, BEGIN_DD, null, false);
                dd = searchFirst(dd, ">", null, false).trim();

                List<String> values = parsedDlTag.get(dt);
                if (values == null) {
                    values = new ArrayList<String>();
                    parsedDlTag.put(dt, values);
                }
                values.add(dd);
            }

            parsedDlTags.add(parsedDlTag);
        }

        return parsedDlTags;
    }

    public static List<String> search(String source, String begin, String... delims) {
        begin = begin == null ? "" : begin;
        delims = delims == null || delims.length == 0 ? new String[] { "" } : delims;
        List<String> searchList = new ArrayList<String>();

        if (source != null) {
            int indexOfBegin = source.indexOf(begin);
            if (indexOfBegin >= 0) {
                source = source.substring(indexOfBegin + begin.length());
                for (String delim : delims) {
                    int indexOfDelim;
                    delim = delim == null ? "" : delim;
                    if (delim.isEmpty() || (indexOfDelim = source.indexOf(delim)) < 0) {
                        searchList.add(source);
                        break;
                    }

                    searchList.add(source.substring(0, indexOfDelim));
                    source = source.substring(indexOfDelim + delim.length());
                }
            }
        }

        return searchList;
    }

    public static List<String> split(String str, String delim) {
        StringTokenizer tokenizer = new StringTokenizer(str, delim);
        List<String> tokens = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

}
