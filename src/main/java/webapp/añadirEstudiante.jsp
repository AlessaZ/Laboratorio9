
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="idUni" type="java.lang.Integer" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Añadir Estudiante"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Añadir Estudiante</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/estudiantes">
                        <div class="mb-3">
                            <label for="nombreEst">Nombre</label>
                            <input class="form-control" type="text" name="nombreEst" id="nombreEst">
                        </div>
                        <div class="mb-3">
                            <label for="apellidoEst">Apellido</label>
                            <input class="form-control" type="text" name="apellidoEst" id="apellidoEst">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Código</label>
                            <input class="form-control" type="text" name="codigo" id="codigo">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Promedio</label>
                            <input class="form-control" type="text" name="promedio" id="promedio">
                        </div>
                        <div class="mb-3">
                            <input class="form-control" type="hidden" name="idUni" value="<%=idUni%>">
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
