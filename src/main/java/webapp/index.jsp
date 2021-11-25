<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <!--Colocar como value: nombre de la página actual -->
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Consorcio"/>
    </jsp:include>
    <body style="background-color: beige">
        <div class='container' >
            <div class="row">
                <div class="col-1  mt-2">
                    <p><b>CMU VIP</b></p>
                </div>
                <div class="col-10 mt-2">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                    <img  class="icon" src="img/img.png" style="max-block-size: 25px;filter: grayscale(100%)">
                </div>
            </div>
            <!-- Incluir el navbar indicando el nombre de página correcto para que se señale la sección actual -->
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value=""/>
            </jsp:include>
            <div class="container text-center mt-2">
                <h1>Bienvenido al consorcio mundial de universidades VIP</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-2"></div>
            <div class="col-4" style="font-family: monospace;align-items: center; display: flex;justify-content: center;
 ">
                <h4>Esta es una pagina donde puedes conocer que universidades pertenecen al consorcio asicomo
                    quienes participan y que alumnos pertecen a las universidades e incluso actulizar la base
                    de datos; todo esto y mucho mas.</h4>
            </div>
            <div class="col-6 mt-5">
                <img  class="icon" src="img/img_1.png" style=";background-position: 50%;border-radius: 50%;
    background-size: 100% auto;max-block-size: 350px">
            </div>
        </div>

        <jsp:include page="/static/scripts.jsp"/>

    </body>
</html>