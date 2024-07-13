<h1 align="center">webapp-cookie-tarea4</h1>
<p>Para este nuevo desafío se requiere el uso de formulario y cookies para cambiar los estilos de la aplicación, el objetivo consiste en crear un pequeño formulario con las opciones de colores.</p>
<h3>Requerimientos</h3>

- Crear un proyecto llamado <b>webapp-cookie-tarea4</b>
- Crear una vista <b>index.jsp</b> con un titulo h3 con el atributo style para dar un estilo css color, de la siguiente forma
```jsp
<h3 style="color: ${cookie.color.getValue()}">Tarea 4: cambiar el color de los textos</h3>
```

Con `${cookie.color.getValue()}` obtenemos el valor de la cookie en una jsp, donde `color` es el nombre de la cookie.

- Ademas tener un párrafo con el elemento <p> con un texto cualquiera que también cambie de color según la cookie.
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

