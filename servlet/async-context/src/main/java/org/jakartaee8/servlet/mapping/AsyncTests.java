/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id:$
 */
package org.jakartaee8.servlet.mapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AsyncTests extends GenericServlet {

    private static final long serialVersionUID = 1L;

    private static final String TEST_HEADER = "testname";
    private static final Class<?>[] TEST_ARGS = { ServletRequest.class, ServletResponse.class };

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String test = req.getParameter(TEST_HEADER);
        try {
            getClass().getMethod(test, TEST_ARGS)
                      .invoke(this, new Object[] { req, res });
            
        } catch (InvocationTargetException ite) {
            throw new ServletException(ite.getTargetException());
        } catch (NoSuchMethodException nsme) {
            throw new ServletException("Test: " + test + " does not exist");
        } catch (Throwable t) {
            throw new ServletException("Error executing test: " + test, t);
        }
    }

    public void asyncTest(ServletRequest request, ServletResponse response) throws IOException {
        response.getWriter().println("ASYNC_STARTED_asyncTest");
        response.getWriter().println("IsAsyncSupported=" + request.isAsyncSupported());
        response.getWriter().println("IsAsyncStarted=" + request.isAsyncStarted());
        response.getWriter().println("DispatcherType=" + request.getDispatcherType());
    }
}
