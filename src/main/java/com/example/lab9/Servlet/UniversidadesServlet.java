package com.example.lab9.Servlet;

import com.example.lab9.Beans.Continente;
import com.example.lab9.Beans.Universidad;
import com.example.lab9.Daos.EstudianteDao;
import com.example.lab9.Daos.PaisDao;
import com.example.lab9.Daos.UniversidadesDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UniversidadesServlet", value = "/universidades")
public class UniversidadesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action")==null?"listar":request.getParameter("action");
        String orden = request.getParameter("orden")==null?"todos":request.getParameter("orden");
        RequestDispatcher view;
        EstudianteDao estudianteDao=new EstudianteDao();
        PaisDao paisDao = new PaisDao();
        UniversidadesDao universidadesDao= new UniversidadesDao();
            switch(action){
            case "listar":
                if(orden.equals("todos")){
                    request.setAttribute("listaUniversidades", universidadesDao.listaUniversidad());
                }else{
                    request.setAttribute("listaUniversidades",universidadesDao.listaUniversidadesOrder(orden));
                }
                Continente continente=new Continente();
                request.setAttribute("continente",continente);
                view = request.getRequestDispatcher("listaUniversidades.jsp");
                view.forward(request, response);
                break;
            case "anadir":
                String er= request.getParameter("er") == null ? "" : request.getParameter("er");
                request.setAttribute("listaPais", paisDao.listaPais());
                if(er.equals("1")){
                    request.setAttribute("er","Se ha ingresado el nombre de una unicersidad existente.");
                }
                view = request.getRequestDispatcher("añadirUniversidad.jsp");
                view.forward(request, response);
                break;
            case "editar":
                request.setAttribute("listaPais", paisDao.listaPais());
                String er1= request.getParameter("er") == null ? "" : request.getParameter("er");
                String idUniversidadStr = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                int idUni = Integer.parseInt(idUniversidadStr);
                Universidad universidad1 = universidadesDao.obtenerUniversidad(idUni);
                if (universidad1 == null) {
                    response.sendRedirect(request.getContextPath() + "/universidades");
                } else {
                    if(er1.equals("1")){
                        request.setAttribute("er","Se ha ingresado el nombre de una unicersidad existente.");
                    }
                    request.setAttribute("universidad", universidad1);
                    view = request.getRequestDispatcher("editarUniversidad.jsp");
                    view.forward(request, response);
                }
                break;
            case "eliminar":
                String idUniversidadStr1 = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                int idUni1 = Integer.parseInt(idUniversidadStr1);
                String idPaisStr = request.getParameter("idPais") != null ? request.getParameter("idPais") : "";
                int idPais= Integer.parseInt(idPaisStr);
                Universidad universidad2 = universidadesDao.obtenerUniversidad(idUni1);
                if (universidad2 != null) {
                    universidadesDao.eliminarEstudiantesU(idUni1);
                    universidadesDao.eliminarUniversidad(idUni1);
                    if(universidadesDao.paisHasU(idPais)){
                        paisDao.eliminarPais(idPais);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/universidades");
                break;
            case "verEst":
                String idUniversidadStr2 = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                int idUni2 = Integer.parseInt(idUniversidadStr2);
                Universidad universidad3 = universidadesDao.obtenerUniversidad(idUni2);
                if (universidad3 != null) {
                    request.setAttribute("universidad",universidad3);
                    request.setAttribute("listaEstudiantes", estudianteDao.listaEstudianteFiltrada(idUni2));
                    view = request.getRequestDispatcher("listaEstudiantes.jsp");
                    view.forward(request, response);
                }
                break;
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UniversidadesDao universidadesDao= new UniversidadesDao();
        String action = request.getParameter("action") != null ? request.getParameter("action"): "crear";
        switch (action){
            case "crear":
                String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String ranking = request.getParameter("rank") != null ? request.getParameter("rank") : "";
                String foto = request.getParameter("foto") != null ? request.getParameter("foto"): "";
                String idpais = request.getParameter("idpais")  != null ? request.getParameter("idpais"): "";
                int rankingInt = Integer.parseInt(ranking);
                int idPaisInt = Integer.parseInt(idpais);
                if(universidadesDao.universidadIsLista(nombre)){
                    universidadesDao.añadirUniversidad(nombre,rankingInt,foto,idPaisInt);
                    response.sendRedirect(request.getContextPath() + "/universidades");
                }else{
                    response.sendRedirect(request.getContextPath() + "/universidades?action=anadir&er=1");
                }

                break;
            case "actualizar":
                String idUniStr = request.getParameter("idUniversidad")  != null ? request.getParameter("idUniversidad"): "";
                String nombre2 = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String ranking2 = request.getParameter("rank") != null ? request.getParameter("rank") : "";
                String foto2 = request.getParameter("foto") != null ? request.getParameter("foto"): "";
                String idpais2 = request.getParameter("idpais")  != null ? request.getParameter("idpais"): "";
                int rankingInt2 = Integer.parseInt(ranking2);
                int idPaisInt2 = Integer.parseInt(idpais2);
                int idUni=Integer.parseInt(idUniStr);
                System.out.println("SERVLET"+nombre2);
                if(universidadesDao.universidadIsLista(nombre2)){
                    universidadesDao.editarUniversidad(idUni,nombre2,rankingInt2,foto2,idPaisInt2);
                    response.sendRedirect(request.getContextPath() + "/universidades");
                }else{
                    response.sendRedirect(request.getContextPath() + "/universidades?action=editar&er=1&id="+idUniStr);
                }

                break;
        }
    }
}
