package org.jakartaee8.servlet.servletrequest.servletcontext.attributelistener;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import java.io.File;
import java.net.URL;

import org.jakartaee8.urlclient.ServletTestUtil;
import org.jakartaee8.urlclient.StaticLog;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author Arjan Tijms
 */
@ExtendWith(ArquillianExtension.class)
@ExtendWith(ServletContextAttributeListenerTest.CountingExtension.class)
public class ServletContextAttributeListenerTest {

    @ArquillianResource
    private URL base;
    private ServletContextAttributeListenerClient servletContextAttributeListenerClient;
    private static int count;

    public static class CountingExtension implements BeforeEachCallback {
        @Override
        public void beforeEach(ExtensionContext extensionContext) throws Exception {
            count = count + 1;
            System.out.println("\n\nStarting test " + count + ": " + extensionContext.getRequiredTestMethod().getName());
        }
    };

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        try {
            return create(WebArchive.class)
                    .addClasses(
                        GenericTCKServlet.class,
                        TestServletContextAttributeListener.class,
                        TestServlet.class,
                        StaticLog.class,
                        ServletTestUtil.class
                    )
                    .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"))

                    ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setup() {
        servletContextAttributeListenerClient = new ServletContextAttributeListenerClient(base, "TestServlet");
    }


    /* Run test */


    /*
     * @testName: constructorTest
     *
     * @assertion_ids: Servlet:JAVADOC:112
     *
     * @test_Strategy: Servlet instanciate the constructor
     */
    @Test
    @RunAsClient
    public void constructorTest() throws Exception {
        servletContextAttributeListenerClient.constructorTest();
    }

    /*
     * @testName: addedTest
     *
     * @assertion_ids: Servlet:JAVADOC:113;Servlet:JAVADOC:114;Servlet:JAVADOC:117
     *
     * @test_Strategy:
     * Servlet adds an attribute. The listener should detect the
     * add and write a message out to a static log.
     * Servlet then reads the log and verifies the result. It also verifies the request and context that changed
     *
     */
    @Test
    @RunAsClient
    public void addedTest() throws Exception {
        servletContextAttributeListenerClient.addedTest();
    }

    /*
     * @testName: removedTest
     *
     * @assertion_ids: Servlet:JAVADOC:113;Servlet:JAVADOC:115;Servlet:JAVADOC:117
     *
     * @test_Strategy:
     * Servlet adds/removes an attribute. The listener should
     * detect the add and write a message out to a static log. Servlet then reads
     * the log and verifys the result. It also verifies the request and context
     * that changed
     */
    @Test
    @RunAsClient
    public void removedTest() throws Exception {
        servletContextAttributeListenerClient.removedTest();
    }

    /*
     * @testName: replacedTest
     *
     * @assertion_ids: Servlet:JAVADOC:113;Servlet:JAVADOC:116;Servlet:JAVADOC:117
     *
     * @test_Strategy:
     * Servlet adds/replaces an attribute. The listener should
     * detect the add and write a message out to a static log. Servlet then reads
     * the log and verifys the result. It also verifies the request and context
     * that changed
     */
    @Test
    @RunAsClient
    public void replacedTest() throws Exception {
        servletContextAttributeListenerClient.replacedTest();
    }

}
