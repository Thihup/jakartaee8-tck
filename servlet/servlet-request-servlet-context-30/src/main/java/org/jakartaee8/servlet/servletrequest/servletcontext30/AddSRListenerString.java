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
 * $Id$
 */

package org.jakartaee8.servlet.servletrequest.servletcontext30;

import java.util.ArrayList;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public final class AddSRListenerString implements ServletRequestListener {

    // Public Methods

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        ArrayList al = null;
        al = (ArrayList) event.getServletContext().getAttribute("arraylist");
        if (al != null) {
            al.add("in requestDestroyed method of AddSRListenerString");
            event.getServletContext().setAttribute("arraylist", al);
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        ArrayList al = null;
        al = (ArrayList) event.getServletContext().getAttribute("arraylist");
        if (al == null) {
            al = new ArrayList();
        }
        al.add("AddSRListenerString_INVOKED");
        event.getServletContext().setAttribute("arraylist", al);
    }

}
