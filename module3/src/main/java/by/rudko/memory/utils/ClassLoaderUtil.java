package by.rudko.memory.utils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.logging.Logger;

public class ClassLoaderUtil {
    private static final Logger LOGGER = Logger.getLogger(ClassLoaderUtil.class.getName());

    public static byte[] loadByteCode(String clazzName) throws Exception{
        if(clazzName == null){
            return null;
        }
        clazzName = clazzName.replace(".","/").concat(".class");

        try(InputStream is = Thread.currentThread().getContextClassLoader().getResource(clazzName).openStream()) {
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            LOGGER.warning(String.format("Can't read file from source: %s, ERROR: %s",clazzName, e));
        }
        return null;
    }
}