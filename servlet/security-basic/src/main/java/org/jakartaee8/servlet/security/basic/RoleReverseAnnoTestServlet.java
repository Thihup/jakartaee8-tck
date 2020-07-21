/*
 * Copyright (c) 2012, 2018 Oracle and/or its affiliates. All rights reserved.
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

package org.jakartaee8.servlet.security.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * This should require employee role permissions to access the Get or Post
 * methods.
 *
 * <p>
 * This should be an equivalent to RoleReverseTest with the main difference being that
 * this uses Servlet based annotations whereas RoleReverseTest uses deployment descriptor
 * for configuring its security constraints.
 *
 * <p>
 * It is worth noting that all methods (besides GET and POST) do NOT* have any security constraints
 * on them and so are unprotected and the access to Get & POST requires you to be an employee role.
 *
 */
@DeclareRoles({ "Administrator", "Manager", "VP", "Employee" })
@ServletSecurity(
    httpMethodConstraints = {
        @HttpMethodConstraint(value = "GET", rolesAllowed = "Employee"),
        @HttpMethodConstraint(value = "POST", rolesAllowed = "Employee")})
@WebServlet(name = "RoleReverseAnnoTestLogicalName", urlPatterns = { "/RoleReverseAnnoTest" })
public class RoleReverseAnnoTestServlet extends HttpServlet {

    private static final long serialVersionUID = -5537912077923586852L;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("The user principal is: " + request.getUserPrincipal().getName() + "<BR>");

        // Output whether the user is in any of the known or an unknown role.
        //
        // Surround these with !'s so they are easier to search for.
        // (i.e. we can search for !true! or !false!)
        out.println("isUserInRole(\"ADM\"): !" + request.isUserInRole("ADM") + "!<BR>");
        out.println("isUserInRole(\"MGR\"): !" + request.isUserInRole("MGR") + "!<BR>");
        out.println("isUserInRole(\"VP\"): !" + request.isUserInRole("VP") + "!<BR>");
        out.println("isUserInRole(\"EMP\"): !" + request.isUserInRole("EMP") + "!<BR>");
    }
}
