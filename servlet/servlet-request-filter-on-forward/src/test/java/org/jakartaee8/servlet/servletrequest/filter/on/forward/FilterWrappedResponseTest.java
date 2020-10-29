package org.jakartaee8.servlet.servletrequest.filter.on.forward;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import java.io.File;
import java.net.URL;

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
@ExtendWith(FilterWrappedResponseTest.CountingExtension.class)
public class FilterWrappedResponseTest {

    @ArquillianResource
    private URL base;
    private FilterWrappedResponseClient filterWrappedResponseClient;
    private static int count;

    public static class CountingExtension implements BeforeEachCallback {
        @Override
        public void beforeEach(ExtensionContext extensionContext) throws Exception {
            count = count + 1;
            System.out.println("\n\nStarting test " + count + ": " + extensionContext.getRequiredTestMethod().getName());
        }
    }


    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        try {
            return create(WebArchive.class)
                    .addClasses(
                        GenericTCKServlet.class,
                        TestServlet.class,
                        CTSResponseWrapper.class,
                        ForwardedServlet.class,
                        ServletTestUtil.class,
                        WrapResponseFilter.class
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
        filterWrappedResponseClient = new FilterWrappedResponseClient(base, "TestServlet");
    }


    /* Run test */
    /*
     * @testName: wrapResponseTest
     *
     * @assertion_ids: Servlet:SPEC:54; Servlet:SPEC:59;
     *
     * @test_Strategy:
     *     1. Create two servlets - TestServlet, ForwardedServlet
     *     2. Invoke ForwardedServlet using forward in TestServlet
     *     3. Map a filter WrapResponseFilter with dispatcher value set to FORWARD
     *     4. In the filter, wrap the response with custom implementation of ServletResponse CTSResponseWrapper
     *     5. Verify that filter is properly invoked.
     */
    @Test
    @RunAsClient
    public void dispatchReturnTest() throws Exception {
        filterWrappedResponseClient.wrapResponseTest();
    }

}
