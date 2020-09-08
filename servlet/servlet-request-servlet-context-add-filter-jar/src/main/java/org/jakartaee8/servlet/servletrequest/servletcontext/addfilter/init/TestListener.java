/*
 * Copyright (c) 2013, 2018 Oracle and/or its affiliates. All rights reserved.
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
 * $Id$
 */
package org.jakartaee8.servlet.servletrequest.servletcontext.addfilter.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestListener implements ServletContextListener {

    /**
     * Receives notification that the web application initialization process is starting.
     *
     * @param servletContextEvent The ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        boolean passed = true;
        ServletContext context = servletContextEvent.getServletContext();
        StringBuilder log = new StringBuilder();

        try {
            context.addFilter("AddFilterString", "org.jakartaee8.servlet.servletrequest.servletcontext.addfilter.init.AddFilterString");
            passed = false;
            log.append("Expected UnsupportedOperationException not thrown.");
        } catch (UnsupportedOperationException ex) {
            log.append("Expected UnsupportedOperationException thrown.");
        }

        context.setAttribute("TCK_TEST_STATUS", log.toString());
        context.setAttribute("TCK_TEST_PASS_STATUS", passed);
    }

    /**
     * Receives notification that the servlet context is about to be shut down.
     *
     * @param sce The servlet context event
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
