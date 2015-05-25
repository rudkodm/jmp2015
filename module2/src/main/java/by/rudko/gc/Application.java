package by.rudko.gc;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import static java.lang.System.err;
import static java.lang.System.out;

public class Application {
    private static final String TEST_TYPE = System.getProperty("test.type");
    private static final int OBJECT_SIZE = Integer.valueOf(System.getProperty("test.object.size"));

    public static void main(String[] args) throws Exception {
        switch (Command.toEnum(TEST_TYPE)) {
            case TEST_YONG_GEN: {
                testYong();
                break;
            }
            case TEST_OLD_GET: {
                testOld();
                break;
            }
            default: {
                err.printf("There is no such property: %s", TEST_TYPE);
            }
        }


    }

    private static void testYong() {
        while (true) {
            process(new Bean(OBJECT_SIZE));
        }
    }

    private static void testOld() {
    	SoftReference<ArrayList<Bean>> ref = new SoftReference<ArrayList<Bean>>(new ArrayList<Bean>());
    	
        while (true) {

            Bean o = new Bean(OBJECT_SIZE);
            if(ref.get() == null) {
            	ref = new SoftReference<ArrayList<Bean>>(new ArrayList<Bean>());
            }
            ref.get().add(o);

            process(o);
        }
    }

    private static void process(Bean o) {
        int id = o.getId();
        out.println(id);
        if(id == 1000000) System.exit(0);
    }
}
