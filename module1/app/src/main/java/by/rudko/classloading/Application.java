package by.rudko.classloading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Application {
    private static final Logger LOG = LogManager.getLogger(Application.class.getName());
    private static final ModuleFactory factory = ModuleFactory.getInstance();
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //file:/home/rudkodm/workspace/jmp2015/module1/plugin/build/libs/plugin.jar
    //file:d:/workspace/jmp2015/module1/plugin/build/libs/plugin.jar
    public static void main(String[] args) throws Exception {
    //System.out.println(Arrays.toString(((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs()));

        while (true) {
            LOG.info("COMMAND:");

            try {
                Command command = Command.toEnum(in.readLine());
                Module module = command.getCommandHandler().process(in, factory);
                runModule(module);
            } catch (NoSuchElementException e1) {
                LOG.error("No such command. Try again");
            } catch (NoSuchFileException e2) {
                LOG.error("No such resource:", e2);
            } catch (Exception e3) {
                LOG.error("Some processing error", e3);
            }
        }
    }

    private static void runModule(Module module) {
        module.load();
        module.run();
        module.unload();
    }


}
