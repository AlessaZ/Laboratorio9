<%@ page import="com.example.lab9.Beans.Pais" %>
<%@ page import="com.example.lab9.Beans.Universidad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Universidad>" scope="request" id="listaUniversidad"/>
<jsp:useBean id="idUni" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="estudiante" type="com.example.lab9.Beans.Estudiante" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Editar estudiante"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Editar Estudiante</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/estudiantes?action=actualizar">
                        <div class="mb-3">
                            <input class="form-control" type="hidden" name="idEst" value="<%=estudiante.getIdEstudiante()%>">
                        </div>
                        <div class="mb-3">
                            <label for="nombreEst">Nombre</label>
                            <input class="form-control" type="text" name="nombreEst" id="nombreEst" value="<%=estudiante.getNombreParticipante()%>">
                        </div>
                        <div class="mb-3">
                            <label for="apellidoEst">Apellido</label>
                            <input class="form-control" type="text" name="apellidoEst" id="apellidoEst" value="<%=estudiante.getApellidoParticipante()%>">
                        </div>
                        <div class="mb-3">
                            <label for="codigo">Código</label>
                            <input class="form-control" type="text" name="codigo" id="codigo" value="<%=estudiante.getCodigoAlumno()%>">
                        </div>
                        <div class="mb-3">
                            <label for="promedio">Promedio</label>
                            <input class="form-control" type="text" name="promedio" id="promedio" value="<%=estudiante.getPromedio()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Universidad</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="idUni">
                                <%for(Universidad uni : listaUniversidad){%>
                                <option value="<%=uni.getIdUniversidad()%>"<%= uni.getIdUniversidad()== estudiante.getUniversidad().getIdUniversidad() ? "selected" : ""%>><%=uni.getNombreUniversidad()%></option>
                                <%}%>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/estudiantes">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>