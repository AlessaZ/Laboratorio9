
<%@ page import="com.example.lab9.Beans.Estudiante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Estudiante>" scope="request" id="listaEstudiantes"/>
<jsp:useBean id="universidad" type="com.example.lab9.Beans.Universidad" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Estudiantes"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>
            <div class="mt-2 text-center">
                <h1>Lista de Estudiantes de <%=universidad.getNombreUniversidad()%></h1>
            </div>

            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <div class="card bg-dark text-white card-img-top img-fluid">
                        <img src="https://unamenchina.files.wordpress.com/2017/03/alumnos-unam-2017-portada.jpg?w=2000&h=1140&crop=1" class="card-img" style="height: auto;width: auto;max-width: 1300px;max-height: 200px"  >
                        <div class="card-img-overlay">
                            <h3 class="card-title">Lista de Estudiantes</h3>
                            <p class="card-text">A continuación, se mostrará la lista de los estudiantes que forman parte de esta gran comunidad ordenados por mejor promedio ponderado...</p>
                            <a class="btn btn-info" href="<%=request.getContextPath()%>/estudiantes?action=añadir&&idUni=<%=universidad.getIdUniversidad()%>">Añadir Estudiante</a>
                        </div>
                    </div>
                    <br>
                    <br>
                    <table class="table">
                        <thead>
                            <tr class="table-dark">
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Edad</th>
                                <th>Código</th>
                                <th>Promedio</th>
                                <th>Condición</th>
                                <th>Actualizar</th>
                                <th>Eliminar</th>
                                <th>Borrar</th>
                            </tr>
                        </thead>
                        <tbody class="table-transparent table-hover">
                            <%for(Estudiante est: listaEstudiantes){%>
                            <tr class="table-light">
                            <td><%=est.getNombreParticipante()%></td>
                            <td><%=est.getApellidoParticipante()%></td>
                            <td><%=est.getEdad()%></td>
                            <td><%=est.getCodigoAlumno()%></td>
                            <td><%=est.getPromedio()%></td>
                            <td style="<%=est.getCondicion().ColorTexto()%>"><%=est.getCondicion().getNombre()%></td>
                            <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/estudiantes?action=editar&id=<%=est.getIdEstudiante()%>&idUni=<%=est.getIdUniversidad()%>&idPais=<%=universidad.getPais().getIdPais()%>"><span class="fa fa-edit"></span></a></td>
                            <%if(est.getCondicion().getNombre().equalsIgnoreCase("eliminado")){%>
                            <td><a href="#" class="btn btn-secondary disabled" tabindex="-1" role="button" aria-disabled="true"><i class="bi bi-person-x"></i></a></td>
                            <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/estudiantes?action=borrar&id=<%=est.getIdEstudiante()%>"><span class="fa fa-trash"></span></a></td>
                            <%}else{%>
                            <td><a class="btn btn-warning" href="<%=request.getContextPath()%>/estudiantes?action=eliminar&id=<%=est.getIdEstudiante()%>"><i class="bi bi-person-x"></i></a></td>
                            <td><a href="#" class="btn btn-secondary disabled" tabindex="-1" role="button" aria-disabled="true"><span class="fa fa-trash"></span></a></td>
                            <%}%>
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


