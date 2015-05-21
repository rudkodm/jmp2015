package by.rudko.gc;

import java.lang.ref.SoftReference;
import java.util.HashSet;

import static java.lang.System.err;
import static java.lang.System.out;

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
        final HashSet set = new HashSet();

        while (true) {

            Bean o = new Bean();
            out.println(o.getId());

            set.add(new SoftReference<Bean>(o));
        }
    }
}
