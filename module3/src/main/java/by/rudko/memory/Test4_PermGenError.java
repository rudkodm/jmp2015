package by.rudko.memory;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class Test4_PermGenError {
    public static void main(String[] args) throws Exception {
        method1();
    }

    private static void method1() throws Exception {
        String clazzName = Test4_PermGenError.class.getName();
        byte[] bytes = ClassLoaderUtil.getClassBytes(clazzName);
        System.out.println("Bytes size:" + bytes.length);
        
        ArrayList<Object> list = new ArrayList<Object>();
        while(true){
            list.add(new CustomClassLoader(bytes).loadClass(clazzName));
        }
    }
}



class CustomClassLoader extends ClassLoader{
    private final byte[] bytes;

    public CustomClassLoader(byte[] bytes){
        this.bytes = bytes;
    }

    @Override
    public Class<?> loadClass(String name) {
        try {
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
        	try {
                return getSystemClassLoader().loadClass(name);
            } catch (ClassNotFoundException e1) {
                return null;
            }
        }
    }
}

class ClassLoaderUtil {
    private static final Logger LOGGER = Logger.getLogger(ClassLoaderUtil.class.getName());

    public static byte[] getClassBytes(String clazzName) throws Exception{
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

interface I{}
