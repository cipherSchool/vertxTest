package com.example.demo.common.utilities;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Bootstrap {

    public void createDependencies () {

        String classContent = "com.example.demo.utilities; public class DefineDependencies { static { System.out.println(\"hello\"); } public DefineDependencies() { System.out.println(\"world\"); } }";

        try {
            Path path = Paths.get("");
            File sourceFile = new File(path+"DefineDependencies.java");
            Files.write(sourceFile.toPath(), classContent.getBytes(StandardCharsets.UTF_8));
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, sourceFile.getPath());
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }


    }




/*
    // On Windows running on C:\, this is C:\java.
    File sourceFile = new File(path, "test/DefineDependencies.java");
    sourceFile.getParentFile().mkdirs();
    Files.write(sourceFile.toPath(), classContent.getBytes(StandardCharsets.UTF_8));

    // Compile source file.
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    compiler.run(null, null, null, sourceFile.getPath());

    // Load and instantiate compiled class.
    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
    Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
    Object instance = cls.newInstance(); // Should print "world".
    System.out.println(instance); // Should print "test.Test@hashcode".
*/

}
