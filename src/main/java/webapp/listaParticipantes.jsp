
<%@ page import="com.example.lab9.Beans.Participante" %>
<%@ page import="com.example.lab9.DTO.PorcentajeHyMDto" %>
<%@ page import="com.example.lab9.DTO.PaisMayorNumParticipantesDto" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Participante>" scope="request" id="listaParticipante"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.DTO.PorcentajeHyMDto>" scope="request" id="listaEGenero"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.DTO.PaisMayorNumParticipantesDto>" scope="request" id="listaEPais"/>
<jsp:useBean id="promEdad" type="com.example.lab9.DTO.PromEdadPartDto" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Participantes"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="participantes"/>
            </jsp:include>

                <div class="main-content container-fluid">
                    <div class="card bg-dark text-white card-img-top img-fluid">
                        <img src="https://unamenchina.files.wordpress.com/2017/03/alumnos-unam-2017-portada.jpg?w=2000&h=1140&crop=1" class="card-img" style="height: auto;width: auto;max-width: 1300px;max-height: 200px" >
                        <div class="card-img-overlay">
                            <h3 class="card-title">Lista de Participantes</h3>
                            <p class="card-text">A continuación se mostrará la lista de participantes, los cuales están ordenados por orden de antiguedad...</p>
                            <a class="btn btn-warning" href="<%=request.getContextPath()%>/participantes?action=anadir">Añadir Participante</a>
                        </div>
                    </div>
                    <br>
                    <br>
                    <section class="section">
                        <div class="row mb-4">
                            <div class="col-md-9">
                                <table class="table">
                                    <thead>
                                        <tr class="table-dark">
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Edad</th>
                                            <th>Género</th>
                                            <th>País</th>
                                            <th>Actualizar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-transparent table-hover">
                                        <%for(Participante participante : listaParticipante){%>
                                        <tr class="table-light">
                                            <td><%=participante.getNombreParticipante()%></td>
                                            <td><%=participante.getApellidoParticipante()%></td>
                                            <td><%=participante.getEdad()%></td>
                                            <td><%=participante.getGenero().getGenero()%></td>
                                            <td><%=participante.getPais().getNombrePais()%></td>
                                            <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/participantes?action=editar&id=<%=participante.getIdParticipante()%>"><span class="fa fa-edit"></span></a></td>
                                            <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/participantes?action=eliminar&id=<%=participante.getIdParticipante()%>"><span class="fa fa-trash"></span></a></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-3">
                                <br>
                                <div class="card" style="width: 18rem;">
                                    <img src="https://image.freepik.com/foto-gratis/amigos-joven-chocando-manos_23-2147643723.jpg" class="card-img-top">
                                    <div class="card-body">
                                        <p class="card-text"><h5>Dato:</h5></p>
                                        <div class="card-right d-flex align-items-center">
                                            <p>El promedio de edades de los participantes es de <%=promEdad.getPromedio()%> años.</p>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="card">
                                    <div class="card-header">
                                        La mayoría de participantes se encuentran en ...
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-info table-transparent table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Pais</th>
                                                    <th>Participante</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%for(PaisMayorNumParticipantesDto pr:listaEPais){%>
                                                <tr>
                                                    <td><%=pr.getNombrePais()%></td>
                                                    <td><%=pr.getNumParticipantes()%></td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="card widget-todo my-3">
                                    <div class="card-header border-bottom d-flex justify-content-between align-items-center">
                                        <h4 class="card-title d-flex">
                                            <i class="bx bx-check font-medium-5 pl-25 pr-75"></i>Género
                                        </h4>

                                    </div>
                                    <div class="card-body px-0 py-1">
                                        <table class="table table-borderless">
                                            <tbody>
                                                <tr>
                                                    <%for(PorcentajeHyMDto pt : listaEGenero){%>
                                                    <td class="col-3"><%=pt.getNombreSexo()%></td>
                                                    <td class="col-6">
                                                        <div class="progress progress-info">
                                                            <div class="progress-bar" role="progressbar" style="width: <%=pt.getPorcentaje()%>%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                        </div>
                                                    </td>
                                                <td class="col-3 text-center"><%=pt.getPorcentaje()%>%</td>
                                            </tr>
                                            <%}%>
                                        </tbody></table>
                                </div>
                            </div>
                        </div>
                        </div>
                    </section>
                </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>