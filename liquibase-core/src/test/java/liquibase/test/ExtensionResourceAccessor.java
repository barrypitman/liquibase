package liquibase.test;

import liquibase.resource.ResourceAccessor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @deprecated This class should be removed. If you want to test extensions
 *             place test in liquibase-ext-tests
 *
 *
 */
@Deprecated
public class ExtensionResourceAccessor implements ResourceAccessor {
    private URLClassLoader classLoader;

    public ExtensionResourceAccessor() throws Exception {
        File integrationProjectRoot = TestContext.getInstance().findIntegrationTestProjectRoot();
        File integrationJarsDir = new File(integrationProjectRoot, "src/test/resources/ext/jars");

        File samples1 = new File(integrationJarsDir, "liquibase-sample1.jar");
        File samples2 = new File(integrationJarsDir, "liquibase-sample2.jar");
        File samples3 = new File(integrationProjectRoot.getParentFile(), "samples/liquibase-ext-sample3/target/classes/");

         classLoader = new URLClassLoader(new URL[]{
                samples1.toURL(),
                samples2.toURL(),
                samples3.toURL(),
        });

    }

    public InputStream getResourceAsStream(String file) throws IOException {
        return classLoader.getResourceAsStream(file);
    }

    public Enumeration<URL> getResources(String packageName) throws IOException {
        return classLoader.getResources(packageName);
    }

    public ClassLoader toClassLoader() {
        return classLoader;
    }
}