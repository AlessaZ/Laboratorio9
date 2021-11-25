<%@ page import="com.example.lab9.Beans.Pais" %>
<%@ page import="com.example.lab9.Beans.Continente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Pais>" scope="request" id="listaPais"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Continente>" scope="request" id="listaContinente"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Paises"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="paises"/>
            </jsp:include>

            <div class="card bg-dark text-white card-img-top img-fluid">
                <img src="https://unamenchina.files.wordpress.com/2017/03/alumnos-unam-2017-portada.jpg?w=2000&h=1140&crop=1" class="card-img" style="height: auto;width: auto;max-width: 1300px;max-height: 200px"  >
                <div class="card-img-overlay">
                    <h3 class="card-title">Lista de Paises</h3>
                    <p class="card-text">A continuación, se mostrará la lista de los países que forman parte del consorcio,
                        las cuales han demostrado ser los mejores para el beneficio de la eduación.</p>
                    <p>
                        <a class="btn btn-info" href="<%=request.getContextPath()%>/paises?action=anadir">Añadir Pais</a>
                </div>
            </div>
            <br>
            <br>
                    <table class="table">
                        <thead>
                            <tr class="table-dark">
                                <th>Pais</th>
                                <th>Continente</th>
                                <th>Poblacion(millones)</th>
                                <th>Tamaño(km²)</th>
                                <th>Actualizar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody class="table-transparent table-hover">
                            <%for(Pais pais : listaPais){%>
                            <tr class="table-light">
                            <td><%=pais.getNombrePais()%></td>
                            <td><%=pais.getContinente().getNombre()%></td>
                            <td><%=pais.getPoblacion()%></td>
                            <td><%=pais.getTamanio()%></td>
                            <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/paises?action=editar&id=<%=pais.getIdPais()%>"><span class="fa fa-edit"></span></a></td>
                            <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/paises?action=eliminar&id=<%=pais.getIdPais()%>"><span class="fa fa-trash"></span></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>