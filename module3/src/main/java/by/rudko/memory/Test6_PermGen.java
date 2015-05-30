package by.rudko.memory;

import by.rudko.memory.utils.ClassLoaderUtil;
import by.rudko.memory.utils.CustomClassLoader;

import java.util.logging.Logger;

public class Test6_PermGen {
    private static final Logger LOGGER = Logger.getLogger(Test6_PermGen.class.getName());

    public static void main(String[] args) throws Exception {
        String oldName = Holder_0000000000000000.class.getSimpleName();
        String oldFullName = Holder_0000000000000000.class.getName();
        byte[] oldByteCode = ClassLoaderUtil.loadByteCode(oldFullName);

        LOGGER.info("Bytes size:" + oldByteCode.length);
        LOGGER.info(new String(oldByteCode, "latin1"));

        CustomClassLoader classLoader = new CustomClassLoader();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {

            String newName = construct(oldName, String.valueOf(i));

            String newFullName = oldFullName.replace(oldName, newName);
            byte[] newByteCode = new String(oldByteCode, "latin1")
                    .replaceAll(oldName, newName)
                    .getBytes("latin1");

            classLoader._defineClass(newFullName, newByteCode);

        }
    }

    private static String construct(String oldName, String num) {
        int numLength = num.length();
        return oldName
                .substring(0, oldName.length() - numLength)
                .concat(num);
    }
}

class Holder_0000000000000000 {
    public static final long CONSTANT = 1;
}

