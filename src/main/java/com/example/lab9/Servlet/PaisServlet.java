package com.example.lab9.Servlet;

import com.example.lab9.Beans.Pais;
import com.example.lab9.Daos.PaisDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PaisServlet", value = "/paises")
public class PaisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        RequestDispatcher view;
        PaisDao paisDao = new PaisDao();
        switch (action) {
            case "listar":
                String idCStr = request.getParameter("idC") == null ? "todos": request.getParameter("idC");

                request.setAttribute("listaContinente",paisDao.listaContinentes());
                if(idCStr.equals("todos")){
                    request.setAttribute("listaPais",paisDao.listaPais());
                }else{
                    int idC = Integer.parseInt(idCStr);
                    request.setAttribute("listaPais",paisDao.listaPaisxContinente(idC));
                }
                view = request.getRequestDispatcher("listaPais.jsp");
                view.forward(request, response);
                break;
            case "anadir":
                String err= request.getParameter("err") == null ? "" : request.getParameter("err");
                request.setAttribute("listaContinente",paisDao.listaContinentes());
                if(err.equals("1")){
                    request.setAttribute("er","El nombre ya existe");
                }
                view = request.getRequestDispatcher("añadirPais.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String err1= request.getParameter("err") == null ? "" : request.getParameter("err");
                request.setAttribute("listaContinente",paisDao.listaContinentes());
                String idPaisStr = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idPais=Integer.parseInt(idPaisStr);
                Pais pais = paisDao.obtenerPais(idPais);
                if (pais== null) {
                    response.sendRedirect(request.getContextPath() + "/paises");
                } else {
                    if(err1.equals("1")){
                        request.setAttribute("er","El nombre ya existe");
                    }
                    request.setAttribute("pais",pais );
                    view = request.getRequestDispatcher("editarPais.jsp");
                    view.forward(request, response);
                }
                break;
            case "eliminar":
                String idPaisStr1 = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idPais1=Integer.parseInt(idPaisStr1);
                Pais pais1 = paisDao.obtenerPais(idPais1);
                if (pais1 != null) {
                    paisDao.eliminarPais(idPais1);
                }
                response.sendRedirect(request.getContextPath() + "/paises");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PaisDao paisDao = new PaisDao();
        String action = request.getParameter("action") != null ? request.getParameter("action"): "crear";
        switch (action){
            case "crear":
                String nombre = request.getParameter("nombrePais") != null ? request.getParameter("nombrePais") : "";
                String id = request.getParameter("idContinente") != null ? request.getParameter("idContinente") : "";
                String pobStr = request.getParameter("poblacion") != null ? request.getParameter("poblacion"): "";
                String tamStr = request.getParameter("tamanio")  != null ? request.getParameter("tamanio"): "";
                int idInt = Integer.parseInt(id);
                int pob = Integer.parseInt(pobStr);
                int tam = Integer.parseInt(tamStr);
                ArrayList<Pais> listaPais= paisDao.listaPais();
                Boolean noPais=true;
                for(Pais pais: listaPais) {
                    System.out.println(pais.getNombrePais());
                    if(pais.getNombrePais().equalsIgnoreCase(nombre)){
                        noPais = false;
                        String er="El nombre ya existe";
                        System.out.println("No se puede agregar");
                        break;
                    }
                }
                if(noPais){
                    paisDao.añadirPais(nombre,idInt,pob,tam);
                    response.sendRedirect(request.getContextPath() + "/paises");
                }else{
                    response.sendRedirect(request.getContextPath() + "/paises?action=anadir&err=1");
                }
                break;
            case "actualizar":
                String nombre1 = request.getParameter("nombrePais") != null ? request.getParameter("nombrePais") : "";
                String id1 = request.getParameter("idContinente") != null ? request.getParameter("idContinente") : "";
                String pobStr1 = request.getParameter("poblacion") != null ? request.getParameter("poblacion"): "";
                String tamStr1 = request.getParameter("tamanio")  != null ? request.getParameter("tamanio"): "";
                String idStr = request.getParameter("idPais") != null ? request.getParameter("idPais") : "";
                int idInt1 = Integer.parseInt(id1);
                int pob1 = Integer.parseInt(pobStr1);
                int tam1 = Integer.parseInt(tamStr1);
                int idP=Integer.parseInt(idStr);
                ArrayList<Pais> listaPais1= paisDao.listaPais();
                Boolean noPais1=true;
                for(Pais pais: listaPais1) {
                    System.out.println(pais.getNombrePais());
                    if(pais.getNombrePais().equalsIgnoreCase(nombre1)){
                        noPais1 = false;
                        System.out.println("No se puede editar");
                        break;
                    }
                }
                if(noPais1){
                    paisDao.editarPais(nombre1,idInt1,pob1,tam1,idP);
                    response.sendRedirect(request.getContextPath() + "/paises");
                }else{
                    response.sendRedirect(request.getContextPath() + "/paises?action=editar&err=1"+"&id="+idStr);
                }
                break;
        }
    }
}
