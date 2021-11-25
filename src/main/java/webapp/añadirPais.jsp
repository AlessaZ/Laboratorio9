<%@ page import="com.example.lab9.Beans.Continente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Continente>" scope="request" id="listaContinente"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Añadir Pais"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="paises"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Añadir Pais</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/paises">
                        <div class="mb-3">
                            <label for="nombrePais">Nombre</label>
                            <input class="form-control" type="text" name="nombrePais" id="nombrePais">
                        </div>
                        <div class="text-danger mb-2">
                            <%if(request.getAttribute("er")!=null){%>
                            <%=request.getAttribute("er")%>
                            <%}%>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Continente</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="idContinente">
                                <%for(Continente continente : listaContinente){%>
                                <option value="<%=continente.getIdContinente()%>"><%=continente.getNombre()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="poblacion">Poblacion</label>
                            <input class="form-control" type="text" name="poblacion" id="poblacion">
                        </div>
                        <div class="mb-3">
                            <label for="tamanio">Tamaño Geografico</label>
                            <input class="form-control" type="text" name="tamanio" id="tamanio">
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/paises">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
