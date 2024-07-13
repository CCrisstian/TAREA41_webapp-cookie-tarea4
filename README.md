<h1 align="center">TAREA 41: webapp-cookie-tarea4</h1>
<p>Para este nuevo desafío se requiere el uso de formulario y cookies para cambiar los estilos de la aplicación, el objetivo consiste en crear un pequeño formulario con las opciones de colores.</p>
<h3>Requerimientos</h3>

- Crear un proyecto llamado <b>webapp-cookie-tarea4</b>
- Crear una vista <b>index.jsp</b> con un titulo h3 con el atributo style para dar un estilo css color, de la siguiente forma
```jsp
<h3 style="color: ${cookie.color.getValue()}">Tarea 4: cambiar el color de los textos</h3>
```

Con `${cookie.color.getValue()}` obtenemos el valor de la cookie en una jsp, donde `color` es el nombre de la cookie.

- Ademas tener un párrafo con el elemento `<p>` con un texto cualquiera que también cambie de color según la cookie.
- Ademas de un pequeño formulario solo con un campo select con las opciones de 7 colores css, por ejemplo:
```jsp
                    <select name="color" id="color">
                        <option value="blue">Azul</option>
                        <option value="red">Rojo</option>
                        <option value="green">Verde</option>
                        <option value="aqua">Aqua</option>
                        <option value="BlueViolet">Violeta</option>
                        <option value="Gray">Gris</option>
                        <option value="Cyan">Cyan</option>
                    </select>
```

- El formulario debe tener `method="get"` y el action con la ruta url del servlet encargado de cambiar el color, ejemplo `action="/webapp-cookie-tarea4/cambiar-color"`
- Crear un nuevo servlet llamado <b>CambiarColorServlet</b> mapeado a la ruta `/cambiar-color`, con el método `doGet()` el cual recibe el parámetro color y crea/actualiza la cookie llamada color, luego debe redirigir (con sendRedirect) a la vista index.jsp para refrescar los cambios de la cookie con el nuevo color.

<h1>Solución del Profesor</h1>

Clase servlet CambiarColorServlet:
```java
package org.aguzman.apiservlet.webapp.cookie.tarea4.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/cambiar-color"})
public class CambiarColorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        Cookie colorCookie = new Cookie("color", color);
        resp.addCookie(colorCookie);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
```

Vista jsp index.jsp:
```jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Tarea 4: cambiar el color de los textos</title>
    </head>
    <body>
        <h3 style="color: ${cookie.color.getValue() == null? "black": cookie.color.getValue()}">Tarea 4: cambiar el color de los textos</h3>
        <p style="color: ${cookie.color.getValue() == null? "black": cookie.color.getValue()}">Hola este es un texto que cambia de color segun las opciones</p>

        <form action="/webapp-cookie-tarea4/cambiar-color" method="get">
            <div>
                <label for="color">Cambiar color</label>
                <div>
                    <select name="color" id="color">
                        <option value="blue">Azul</option>
                        <option value="red">Rojo</option>
                        <option value="green">Verde</option>
                        <option value="aqua">Aqua</option>
                        <option value="BlueViolet">Violeta</option>
                        <option value="Gray">Gris</option>
                        <option value="Cyan">Cyan</option>
                    </select>
                </div>

            </div>
            <div>
                <input type="submit" value="Cambiar">
            </div>

        </form>
    </body>
</html>
```

Archivo pom.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.aguzman.apiservlet.webapp.cookie</groupId>
    <artifactId>webapp-cookie-tarea4</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <properties>
        <maven.compiler.source>16</maven.compiler.source>
        <maven.compiler.target>16</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
    <dependencies>
        <dependency>
            <groupId>jakarta.platform</groupId>
            <artifactId>jakarta.jakartaee-api</artifactId>
            <version>9.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.3</version>
        </dependency>

    </dependencies>
    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
            </plugin>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <url>http://localhost:8080/manager/text</url>
                    <username>admin</username>
                    <password>12345</password>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.2.3</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
