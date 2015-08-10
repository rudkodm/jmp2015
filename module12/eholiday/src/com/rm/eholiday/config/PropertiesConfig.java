package com.rm.eholiday.config;

import java.util.*;
import com.rm.eholiday.Log;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PropertiesConfig extends Config {

    private static Log log = Log.newLog("Prop");

    private Map<String, String> properties;

    public PropertiesConfig(String file) {
        super(file);
    }

    protected void initialize(Class clazz) {
        InputStream inputStream = getInputStream();

        Properties loadedProps = new Properties();
        try {
            loadedProps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Config: Unable to load config properties file", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPrivate(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }

            String propName = field.getName();
            String strValue = loadedProps.getProperty(propName);

            Object value;
            Class type = field.getType();
            if (type.equals(String.class)) {
                value = strValue;
            } else if (type.equals(char.class)) {
                if (strValue.length() == 0) {
                    value = null;
                } else if (strValue.length() == 1) {
                    value = strValue.charAt(0);
                } else {
                    String msg = "Invalid char property (" + propName + ") value: " + strValue;
                    log.error(msg);
                    throw new IllegalStateException(msg);
                }
            } else if (type.equals(int.class)) {
                value = Integer.parseInt(strValue);
            } else if (type.equals(long.class)) {
                value = Long.parseLong(strValue);
            } else if (type.equals(double.class)) {
                value = Double.parseDouble(strValue);
            } else if (type.equals(boolean.class)) {
                value = Boolean.parseBoolean(strValue);
            } else if (type.equals(DateFormat.class)) {
                value = new SimpleDateFormat(strValue);
            } else {
                String msg = "Unsupported property type: " + type.getName();
                log.error(msg);
                throw new IllegalStateException(msg);
            }

            try {
                field.setAccessible(true);
                field.set(this, value);
                if (log.isDebug()) {
                    StringBuilder setValueMsg = new StringBuilder("Set ");
                    setValueMsg.append(propName).append("=").append(value);
                    log.debug(setValueMsg);
                }
            } catch (IllegalAccessException e) {
                String msg = "Unable to initialize property: " + propName + " = " + strValue;
                log.error(msg, e);
                throw new IllegalStateException(msg, e);
            }
        }

        properties = Collections.synchronizedMap(new HashMap(loadedProps));
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public Map<String, String> getPropertiesByPrefix(String prefix) {
        Map<String, String> prefixedProps = new HashMap<String, String>();
        for (Map.Entry<String, String> prop : properties.entrySet()) {
            String key = prop.getKey();
            if (key.startsWith(prefix)) {
                key = key.substring(prefix.length());
                prefixedProps.put(key, prop.getValue());
            }
        }
        return prefixedProps;
    }

}
