<%@ page import="com.example.lab9.Beans.Universidad" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Universidad>" scope="request" id="listaUniversidades"/>
<jsp:useBean id="continente" type="com.example.lab9.Beans.Continente" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Universidades"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

        </div>


            <div class="d-flex justify-content-center">
                <div class="w-75 tabla">

                    <div class="card bg-dark text-white card-img-top img-fluid">
                        <img src="https://unamenchina.files.wordpress.com/2017/03/alumnos-unam-2017-portada.jpg?w=2000&h=1140&crop=1" class="card-img" style="height: auto;width: auto;max-width: 1300px;max-height: 200px"  >
                        <div class="card-img-overlay">
                            <h3 class="card-title">Lista de Universidades</h3>
                            <p class="card-text">A continuación, se mostrará la lista de las universidades pertenecientes a los países del consorcio ordenador por cualquier campo que desee...</p>
                            <a class="btn btn-info" href="<%=request.getContextPath()%>/universidades?action=anadir">Añadir Universidad</a>
                        </div>
                    </div>
                    <br>
                    <br>

                    <table class="table">
                        <thead>
                            <tr class="table-dark">
                                <th><a class="breadcrumb-item active" style="color:white"  href="<%=request.getContextPath()%>/universidades?orden=nombreU">Nombre</a></th>
                                <th><a class="breadcrumb-item active" style="color:white" href="<%=request.getContextPath()%>/universidades?orden=pais">Pais</a></th>
                                <th><a class="breadcrumb-item active" style="color:white" href="<%=request.getContextPath()%>/universidades">Ranking</a></th>
                                <th><a class="breadcrumb-item active" style="color:white" href="<%=request.getContextPath()%>/universidades?orden=numAlumnos">Total de alumnos</a></th>
                                <th>Foto</th>
                                <th>Actualizar</th>
                                <th>Eliminar</th>
                                <th>Ver alumnos</th>
                            </tr>
                        </thead>
                        <tbody class="table-transparent table-hover">
                            <%for(Universidad universidad:listaUniversidades){%>
                            <tr class="<%=continente.ColorFila(universidad.getPais().getIdContinente())%>">
                                <td><%=universidad.getNombreUniversidad()%></td>
                                <td><%=universidad.getPais().getNombrePais()%></td>
                                <td><%=universidad.getRanking()%></td>
                                <td><%=universidad.getNumAlumnos()%></td>
                                <td><img style="height: auto;width: auto;max-height: 80px;max-width: 80px" src="<%=universidad.getFotoUniversidad()%>"></td>
                                <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/universidades?action=editar&idUni=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                                <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/universidades?action=eliminar&idUni=<%=universidad.getIdUniversidad()%>&idPais=<%=universidad.getPais().getIdPais()%>"><span class="fa fa-trash"></span></a></td>
                                <td><a class="btn btn-warning" href="<%=request.getContextPath()%>/universidades?action=verEst&idUni=<%=universidad.getIdUniversidad()%>"><span class="fa fa-eye"></span></a></td>
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


