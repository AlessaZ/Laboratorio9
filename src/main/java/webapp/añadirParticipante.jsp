<%@ page import="com.example.lab9.Beans.Pais" %>
<%@ page import="com.example.lab9.Beans.Genero"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Genero>" scope="request" id="listaGenero"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Pais>" scope="request" id="listaPais"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Añadir Participante"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="participantes"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Añadir Participante</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/participantes">
                        <div class="mb-3">
                            <label for="nombrePart">Nombre</label>
                            <input class="form-control" type="text" name="nombrePart" id="nombrePart">
                        </div>
                        <div class="mb-3">
                            <label for="apellidoPart">Apellido</label>
                            <input class="form-control" type="text" name="apellidoPart" id="apellidoPart">
                        </div>
                        <div class="mb-3">
                            <label for="edad">Edad</label>
                            <input class="form-control" type="text" name="edad" id="edad">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Pais</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="idpais">
                                <%for(Pais pais:listaPais){%>
                                <option value="<%=pais.getIdPais()%>"><%=pais.getNombrePais()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Género</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="idgenero">
                                <%for(Genero genero:listaGenero){%>
                                <option value="<%=genero.getIdGenero()%>"><%=genero.getGenero()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="text-danger mb-2">
                            <%if(request.getAttribute("er")!=null){%>
                            <%=request.getAttribute("er")%>
                            <%}%>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/participantes">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>