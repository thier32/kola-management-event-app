package com.notary.management.application.services.impl;

import com.notary.management.application.services.IModuleService;
import com.notary.management.application.services.PluginLoader;
import com.notary.management.kernel.INotaryModule;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModuleService implements IModuleService {

    public List<String> getModuleNames(){
        List<String> listeModules = new ArrayList<>();
        Package[] packages = getClass().getClassLoader().getDefinedPackages();
        for(Package packa : packages){
           //s System.out.println(""+packa.getName());
          Set<Class> classes =  findAllClassesUsingClassLoader(packa.getName());
          if (classes.isEmpty()) continue;;
          System.out.println(classes);
        }
        Arrays.stream(packages).toList().forEach(e -> System.out.println(e.getName()));

        System.out.println("Descriptor name ");

//        PluginLoader pluginLoader = new PluginLoader(new File("."));
//        pluginLoader.loadPlugins();

//        FooFactory f = pluginLoader.getFooFactory("foo");
//        if (f == null) {
//            System.err.println("No factories loaded!");
//            return;
//        }
//
//        System.out.println("This is running from the plugin");
//        final Foo foo = f.build();
//        foo.doFoo();

        listeModules.add("sdfsdfs");
        listeModules.add("sdfsdfssd");
        listeModules.add("sdfsdfssdsdf");
        listeModules.add("sdfsdfssdsdfsd");
        listeModules.add("sdfsdfssdr");
        return listeModules;
    }

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        if (stream == null) return new HashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            Class classe =  Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));

            if (classe.isAssignableFrom(INotaryModule.class)){
                return classe;
            }
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
