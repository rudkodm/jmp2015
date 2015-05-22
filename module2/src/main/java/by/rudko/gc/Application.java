package by.rudko.gc;

import static java.lang.System.err;
import static java.lang.System.out;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws Exception {
        final String key = "test";
        final String value = System.getProperty(key);

        switch (Command.toEnum(value)) {
            case TEST_YONG_GEN: {
                testYong();
                break;
            }
            case TEST_OLD_GET: {
                testOld();
                break;
            }
            default: {
                err.printf("There is no such property %s : %s", key, value);
            }
        }


    }

    private static void testYong() {
        while (true) {
            out.println(new Bean().getId());
        }
    }

    private static void testOld() {
    	SoftReference<ArrayList<Bean>> ref = new SoftReference<ArrayList<Bean>>(new ArrayList<Bean>());
    	
        while (true) {

            Bean o = new Bean();
            out.println(o.getId());
            if(ref.get() == null) {
            	ref = new SoftReference<ArrayList<Bean>>(new ArrayList<Bean>());
            }
            ref.get().add(o);
        }
    }
}
