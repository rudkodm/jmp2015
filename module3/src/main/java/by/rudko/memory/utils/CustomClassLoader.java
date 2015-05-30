package by.rudko.memory.utils;

public class CustomClassLoader extends ClassLoader{
    private final byte[] bytes;

    public CustomClassLoader(){
        this.bytes = null;
    }

    public CustomClassLoader(byte[] bytes){
        this.bytes = bytes;
    }

    public Class<?> loadClassSelf(String name) {
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

    public final Class<?> _defineClass(String name, byte[] byteCode) throws ClassFormatError {
        return defineClass(name, byteCode, 0, byteCode.length, null);
    }


}
