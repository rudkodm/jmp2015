package by.rudko.memory;


import by.rudko.memory.utils.ClassLoaderUtil;
import by.rudko.memory.utils.CustomClassLoader;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Test4_PermGenError {
    private static final Logger LOGGER = Logger.getLogger(Test4_PermGenError.class.getName());

    public static void main(String[] args) throws Exception {
        String clazzName = Test4_PermGenError.class.getName();
        byte[] bytes = ClassLoaderUtil.loadByteCode(clazzName);
        LOGGER.info("Bytes size:" + bytes.length);

        ArrayList<Object> list = new ArrayList<Object>();
        while(true){
            list.add(new CustomClassLoader(bytes).loadClassSelf(clazzName));
        }
    }
}

interface I{}
