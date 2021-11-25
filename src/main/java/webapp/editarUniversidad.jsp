<%@ page import="com.example.lab9.Beans.Pais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Pais>" scope="request" id="listaPais"/>
<jsp:useBean id="universidad" type="com.example.lab9.Beans.Universidad" scope="request"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Editar Universidad"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/universidades?action=actualizar">
                        <div class="mb-3">
                            <input class="form-control" type="hidden"  name="idUniversidad" value="<%=universidad.getIdUniversidad()%>">
                        </div>
                        <div class="mb-3">
                            <label for="nombre">Nombre</label>
                            <input class="form-control" type="text" name="nombre" id="nombre" value="<%=universidad.getNombreUniversidad()%>">
                        </div>
                        <div class="text-danger mb-2">
                            <%if(request.getAttribute("er")!=null){%>
                            <%=request.getAttribute("er")%>
                            <%}%>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Pais</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="idpais">
                                <%for(Pais pais : listaPais){%>
                                <option value="<%=pais.getIdPais()%>"<%= pais.getIdPais()== universidad.getPais().getIdPais() ? "selected" : ""%>><%=pais.getNombrePais()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="rank">Ranking</label>
                            <input class="form-control" type="text" name="rank" id="rank" value="<%=universidad.getRanking()%>">
                        </div>
                        <div class="mb-3">
                            <label for="total">Cantidad de Alumnos</label>
                            <input class="form-control" type="text" disabled name="total" id="total" value="<%=universidad.getRanking()%>">
                        </div>
                        <div class="mb-3">
                            <label for="foto">Foto</label>
                            <input class="form-control" type="text" name="foto" id="foto" value="<%=universidad.getFotoUniversidad()%>">
                        </div>
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/universidades">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
