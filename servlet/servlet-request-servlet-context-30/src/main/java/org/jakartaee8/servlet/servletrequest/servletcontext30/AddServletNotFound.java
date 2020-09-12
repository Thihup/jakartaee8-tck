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
 * $Id:i$
 */

package org.jakartaee8.servlet.servletrequest.servletcontext30;

import static org.jakartaee8.urlclient.ServletTestUtil.printResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jakartaee8.urlclient.StaticLog;

public class AddServletNotFound extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();

        printWriter.println("AddServletNotFound is invoked");
        printResult(printWriter, true);

        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) getServletContext().getAttribute("arraylist");

        for (String tmp : result) {
            printWriter.println(tmp);
        }

        getServletContext().removeAttribute("arraylist");

        result = StaticLog.getClear();
        if (result != null) {
            for (String tmp : result) {
                if (tmp != null) {
                    printWriter.println(tmp);
                }
            }
        }

        StaticLog.clear();
    }
}
