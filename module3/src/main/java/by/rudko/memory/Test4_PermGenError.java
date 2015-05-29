package by.rudko.memory;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Test4_PermGenError {
    public static void main(String[] args) throws Exception {
        method1();
    }

    private static void method1() {
        String clazzName = Test4_PermGenError.class.getName();
        byte[] bytes = ClassLoaderUtil.getClassBytes(clazzName);

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

    public static byte[] getClassBytes(String clazzName) {
        if(clazzName == null){
            return null;
        }
        clazzName = clazzName.replace(".","/").concat(".class");
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(clazzName)
                .getPath();

        byte[] data = null;
        try {
            Path path = Paths.get(absolutePath);
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            LOGGER.warning(String.format("Can't read file from source: %s", clazzName));

        } finally {
            return data;
        }
    }
}

interface I{}
