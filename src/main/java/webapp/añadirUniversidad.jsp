<%@ page import="com.example.lab9.Beans.Pais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Pais>" scope="request" id="listaPais"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Añadir Universidad"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Añadir Universidad</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="post" action="<%=request.getContextPath()%>/universidades">
                        <div class="mb-3">
                            <label for="nombre">Nombre</label>
                            <input class="form-control" type="text" name="nombre" id="nombre">
                        </div>
                        <div class="text-danger mb-2">
                            <%if(request.getAttribute("er")!=null){%>
                            <%=request.getAttribute("er")%>
                            <%}%>
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
                            <label for="rank">Ranking</label>
                            <input class="form-control" type="text" name="rank" id="rank">
                        </div>

                        <div class="mb-3">
                            <label for="foto">Foto</label>
                            <input class="form-control" type="text" name="foto" id="foto">
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/universidades">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>