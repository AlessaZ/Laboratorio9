<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark" style="background-color: #cd383f">
    <a class="navbar-brand" href="#">CONSORCIO DE UNIVERSIDADES</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
           <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle"  href="<%=request.getContextPath()%>/paises" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                    Pais
                </a>
                <ul class="dropdown-menu " aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises?idC=1">America</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises?idC=2">Europa</a></li></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises?idC=3">Asia</a></li></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises?idC=4">Oceania</a></li></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises?idC=5">Australia</a></li></li>
                    <div class="dropdown-divider"></div>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/paises">Todos los paises</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("participantes")? "active": "" %>"
                   href="<%=request.getContextPath()%>/participantes">Participantes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("universidades")? "active": "" %>"
                   href="<%=request.getContextPath()%>/universidades">Universidades</a>
            </li>

        </ul>
    </div>
</nav>
