package by.rudko.classloading.processing;

import by.rudko.classloading.Module;
import by.rudko.classloading.ModuleFactory;

import java.io.BufferedReader;

public class DefaultCommandProcessor implements CommandProcessor {
    @Override
    public Module process(BufferedReader in, ModuleFactory factory) throws Exception {
        return factory.getModule(null);
    }
}
