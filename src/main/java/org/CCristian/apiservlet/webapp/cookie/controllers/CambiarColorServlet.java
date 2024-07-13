package org.CCristian.apiservlet.webapp.cookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/cambiar-color")
public class CambiarColorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el parámetro color de la solicitud
        String color = req.getParameter("color");

        // Crear o Actualizar la Cookie llamada color
        if (color != null && !color.isEmpty()) {
            Cookie colorCookie = new Cookie("color", color);
            // Añadir la cookie a la respuesta
            resp.addCookie(colorCookie);
        }

        // Redirigir a index.jsp
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
