package by.rudko.memory;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Application {
    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        LOG.debug(">> Testing StackOverflowError");
//        System.out.println(buildMethodBody());
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("by.rudko.memory.Method");
        CtMethod m = cc.getDeclaredMethod("invoke");
        m.insertBefore(buildMethodBody());
        Class c = cp.toClass(cc);
        ((Method)c.newInstance()).invoke();
    }

    private static String buildMethodBody() {
        StringBuilder builder = new StringBuilder();
        builder.append("{").append("\r\n");
        for (int i = 0; i < 10; i++) {
            builder.append(String.format("long l%s = %s;", i, i)).append("\r\n");
            builder.append(String.format("System.out.println(l%s);",i)).append("\r\n");
        }
        builder.append("}");
        return builder.toString();
//        return "{ System.out.println(\"haha\"); }";
    }
}

class Method {
    public void invoke(){};
}
