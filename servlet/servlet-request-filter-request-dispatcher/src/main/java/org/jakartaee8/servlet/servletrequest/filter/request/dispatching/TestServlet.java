/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

package org.jakartaee8.servlet.servletrequest.filter.request.dispatching;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestServlet extends GenericTCKServlet {

    private static final long serialVersionUID = 1L;

    public void forwardServletTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/generic/DummyServlet").forward(request, response);
    }

    public void forwardJSPTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/generic/dummyJSP").forward(request, response);
    }

    public void forwardHTMLTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/dummy.html").forward(request, response);
    }

    public void forwardTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/forward/ForwardedServlet").forward(request, response);
    }

    public void includeServletTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/generic/DummyServlet").include(request, response);
    }

    public void includeJSPTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String path = "/generic/dummyJSP";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.include(request, response);
    }

    public void includeHTMLTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String path = "/dummy.html";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.include(request, response);
    }

    public void includeTest(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String path = "/include/IncludedServlet";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.include(request, response);
    }

}
