package by.rudko.memory;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class StackOverflowTest {
    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOG = Logger.getLogger(StackOverflowTest.class);

    public static void main(String[] args) throws Exception {
        LOG.debug(">> Testing StackOverflowError");
        processOnLaptop();
    }

	private static void processOnLaptop() throws NotFoundException, CannotCompileException,
			InstantiationException, IllegalAccessException {
		ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("by.rudko.memory.Methods");
        
        CtMethod m1 = cc.getDeclaredMethod("method1");
        m1.insertBefore(buildMethodBody());
        m1.insertAfter("method2();");

        CtMethod m2 = cc.getDeclaredMethod("method2");
        m2.insertBefore(buildMethodBody());
        m2.insertAfter("method3();");

        CtMethod m3 = cc.getDeclaredMethod("method3");
        m3.insertBefore(buildMethodBody());

        Class c = cp.toClass(cc);
        ((Methods)c.newInstance()).method1();
	}

    private static String buildMethodBody() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3500; i++) {
        	builder.append(String.format("long a%s = %s;", i, i)).append("\r\n");
        }
        return builder.toString();
    }
}

class Methods {
    public void method1(){};
    public void method2(){};
    public void method3(){};
}
