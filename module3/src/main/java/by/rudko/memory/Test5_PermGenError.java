package by.rudko.memory;

import by.rudko.memory.utils.ClassLoaderUtil;
import by.rudko.memory.utils.CustomClassLoader;

import java.util.logging.Logger;

public class Test5_PermGenError {
    private static final String CHAR_ENC = "latin1";
	private static final Logger LOGGER = Logger.getLogger(Test5_PermGenError.class.getName());

    public static void main(String[] args) throws Exception {
        String oldName = Holder_0000000000000000.class.getSimpleName();
        String oldFullName = Holder_0000000000000000.class.getName();
        byte[] oldByteCode = ClassLoaderUtil.loadByteCode(oldFullName);

        LOGGER.info("Bytes size:" + oldByteCode.length);
        LOGGER.info(new String(oldByteCode, CHAR_ENC));

        CustomClassLoader classLoader = new CustomClassLoader();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {

            String newName = construct(oldName, String.valueOf(i));

            String newFullName = oldFullName.replace(oldName, newName);
            byte[] newByteCode = new String(oldByteCode, CHAR_ENC)
                    .replaceAll(oldName, newName)
                    .getBytes(CHAR_ENC);

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

