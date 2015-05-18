package by.rudko.classloading.processing;

import by.rudko.classloading.Module;
import by.rudko.classloading.ModuleFactory;

import java.io.BufferedReader;

public interface CommandProcessor {
    Module process(BufferedReader in, ModuleFactory factory) throws Exception;
}
