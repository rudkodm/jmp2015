package com.rm.eholiday;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import com.rm.eholiday.config.Config;

public class FileUtil {

    private static final Date STARTED_AT = new Date();

    private static final String CHARSET = Config.getFile().getCharset();
    private static final String WORK_DIR = Config.getFile().getWorkDir();
    private static final DateFormat DATE_FORMAT = Config.getFile().getDateFormat();

    private static final Log log = Log.newLog("FileUtil");

    private static String buildFullPath(String name, boolean dated) {
        StringBuilder builder = new StringBuilder(WORK_DIR);
        if (dated) {
            builder.append(File.separator).append(DATE_FORMAT.format(STARTED_AT));
        }
        return builder.append(File.separator).append(name).toString();
    }

    public static File createFile(String name) {
        return createFile(name, false);
    }

    public static File createFile(String name, boolean dated) {
        File file = new File(buildFullPath(name, dated));

        if (!file.exists() || !file.isFile()) {
            File fileDir = file.getParentFile();
            if (!fileDir.exists() && !fileDir.mkdirs()) {
                String msg = "Unable to create directory " + fileDir.getAbsolutePath();
                log.error(msg);
                throw new IllegalStateException(msg);
            }

            String errorMsg = "Unable to create file " + file.getAbsolutePath();
            try {
                if (!file.createNewFile()) {
                    log.error(errorMsg);
                }
            } catch (IOException e) {
                log.error(errorMsg, e);
                throw new IllegalStateException(errorMsg, e);
            }
        }

        return file;
    }

    public static void saveToFile(Set<String> items, String name) {
        saveToFile(items, name, false);
    }

    public static void saveToFile(Set<String> items, String name, boolean dated) {
        File file = createFile(name, dated);

        BufferedWriter buffer = null;
        try {
            OutputStream output = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(output, CHARSET);
            buffer = new BufferedWriter(writer);
            for (String item : items) {
                buffer.write(item);
                buffer.newLine();
            }
            buffer.flush();
        } catch (IOException e) {
            String msg = "Unable to write to file " + file.getAbsolutePath();
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    log.error("Unable to close file " + file.getAbsolutePath());
                }
            }
        }
    }

    public static Set<String> readFromFile(String name) {
        return readFromFile(name, false);
    }

    public static Set<String> readFromFile(String name, boolean dated) {
        File file = new File(buildFullPath(name, dated));

        Set<String> items = null;
        if (file.exists() && file.isFile()) {
            log.info("Reading from file " + file.getAbsolutePath());
            items = new HashSet<String>();

            BufferedReader buffer = null;
            try {
                InputStream input = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(input, CHARSET);
                buffer = new BufferedReader(reader);
                String line;
                while ((line = buffer.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        items.add(line);
                    }
                }
            } catch (IOException e) {
                String msg = "Unable to read file: " + file.getAbsolutePath();
                log.error(msg, e);
                throw new IllegalStateException(msg, e);
            } finally {
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (IOException e) {
                        log.error("Unable to close file " + file.getAbsolutePath());
                    }
                }
            }
        }

        return items;
    }

}
