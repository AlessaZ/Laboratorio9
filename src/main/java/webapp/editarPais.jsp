<%@ page import="com.example.lab9.Beans.Continente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Continente>" scope="request" id="listaContinente"/>
<jsp:useBean id="pais" type="com.example.lab9.Beans.Pais" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Editar Pais"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="paises"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Editar Pais</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/paises?action=actualizar">
                        <div class="mb-3">
                            <input class="form-control" type="hidden"  name="idPais" value="<%=pais.getIdPais()%>">
                        </div>
                        <div class="mb-3">
                            <label for="nombrePais">Nombre</label>
                            <input class="form-control" type="text" name="nombrePais" id="nombrePais" value="<%=pais.getNombrePais()%>">
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
                                <option value="<%=continente.getIdContinente()%>"<%= continente.getIdContinente()== pais.getIdContinente()? "selected" : ""%>><%=continente.getNombre()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="poblacion">Poblacion</label>
                            <input class="form-control" type="text" name="poblacion" id="poblacion" value="<%=pais.getPoblacion()%>">
                        </div>
                        <div class="mb-3">
                            <label for="tamanio">Tamaño Geografico</label>
                            <input class="form-control" type="text" name="tamanio" id="tamanio" value="<%=pais.getTamanio()%>">
                        </div>
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/paises">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
