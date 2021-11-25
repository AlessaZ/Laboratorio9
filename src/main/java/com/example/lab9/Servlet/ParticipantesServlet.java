package com.example.lab9.Servlet;

import com.example.lab9.Beans.Participante;
import com.example.lab9.DTO.PromEdadPartDto;
import com.example.lab9.Daos.PaisDao;
import com.example.lab9.Daos.ParticipanteDao;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ParticipantesServlet", value = "/participantes")
public class ParticipantesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        RequestDispatcher view;
        ParticipanteDao participanteDao = new ParticipanteDao();
        PaisDao paisDao = new PaisDao();
        switch (action) {
            case "listar":
                PromEdadPartDto pd = participanteDao.promedioEdad();
                request.setAttribute("promEdad",pd);
                request.setAttribute("listaEPais",participanteDao.paisNumPart());
                request.setAttribute("listaEGenero",participanteDao.percentHyM());
                request.setAttribute("listaParticipante",participanteDao.listaParticipante());
                view = request.getRequestDispatcher("listaParticipantes.jsp");
                view.forward(request, response);
                break;
            case "anadir":
                String er= request.getParameter("er") == null ? "" : request.getParameter("er");
                if(er.equals("1")){
                    request.setAttribute("er","¡Ha ingresado datos incorrectos! El primer caracter de su nombre y " +
                            "apellido deben comenzar con una letra y debe tener más de 18 años");
                }
                request.setAttribute("listaPais",paisDao.listaPais());
                request.setAttribute("listaGenero",participanteDao.listaGenero());
                view = request.getRequestDispatcher("añadirParticipante.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String er1= request.getParameter("er") == null ? "" : request.getParameter("er");
                request.setAttribute("listaPais",paisDao.listaPais());
                request.setAttribute("listaGenero",participanteDao.listaGenero());
                String idParticipanteStr = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idP=Integer.parseInt(idParticipanteStr);
                Participante participante = participanteDao.obtenerParticipante(idP);
                System.out.println("nombre Part"+participante.getNombreParticipante());
                if (participante== null) {
                    response.sendRedirect(request.getContextPath() + "/participantes");
                } else {
                    if(er1.equals("1")){
                        request.setAttribute("er","¡Ha ingresado datos incorrectos! El primer caracter de su nombre y " +
                                "apellido deben comenzar con una letra y debe tener más de 18 años");
                    }
                    request.setAttribute("participante",participante);
                    view = request.getRequestDispatcher("editarParticipante.jsp");
                    view.forward(request, response);
                }
                break;
            case "eliminar":
                String idParticipanteStr1 = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idP1=Integer.parseInt(idParticipanteStr1);
                Participante participante1 = participanteDao.obtenerParticipante(idP1);
                if (participante1 != null){
                    participanteDao.eliminarParticipante(idP1);
                }
                response.sendRedirect(request.getContextPath() + "/participantes");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ParticipanteDao participanteDao= new ParticipanteDao();
        String action = request.getParameter("action") != null ? request.getParameter("action"): "crear";
        switch (action){
            case "crear":
                String nombre = request.getParameter("nombrePart") != null ? request.getParameter("nombrePart") : "";
                String apellido= request.getParameter("apellidoPart") != null ? request.getParameter("apellidoPart") : "";
                String edadStr = request.getParameter("edad") != null ? request.getParameter("edad"): "";
                String idnacionalidad = request.getParameter("idpais")  != null ? request.getParameter("idpais"): "";
                String idgeneroStr = request.getParameter("idgenero")  != null ? request.getParameter("idgenero"): "";
                int edad = Integer.parseInt(edadStr);
                int idPais = Integer.parseInt(idnacionalidad);
                int idgenero = Integer.parseInt(idgeneroStr);
                Boolean isStrName=participanteDao.esString(nombre);
                Boolean isStrLast=participanteDao.esString(apellido);
                if(isStrName && isStrLast && edad>18){
                    participanteDao.añadirParticipante(nombre,apellido,edad,idgenero,idPais);
                    response.sendRedirect(request.getContextPath() + "/participantes");
                }else{
                    response.sendRedirect(request.getContextPath() + "/participantes?action=anadir&er=1");
                }

                break;
            case "actualizar":
                String idPartStr = request.getParameter("idPart") != null ? request.getParameter("idPart") : "";
                String nombre1 = request.getParameter("nombrePart") != null ? request.getParameter("nombrePart") : "";
                String apellido1= request.getParameter("apellidoPart") != null ? request.getParameter("apellidoPart") : "";
                String edadStr1 = request.getParameter("edad") != null ? request.getParameter("edad"): "";
                String idnacionalidad1 = request.getParameter("idpais")  != null ? request.getParameter("idpais"): "";
                String idgeneroStr1 = request.getParameter("idgenero")  != null ? request.getParameter("idgenero"): "";
                int idPart = Integer.parseInt(idPartStr);
                int edad1 = Integer.parseInt(edadStr1);
                int idPais1 = Integer.parseInt(idnacionalidad1);
                int idgenero1 = Integer.parseInt(idgeneroStr1);
                Boolean isStrName1=participanteDao.esString(nombre1);
                Boolean isStrLast1=participanteDao.esString(apellido1);
                if(isStrName1 && isStrLast1 && edad1>18){
                    participanteDao.editarParticipante(idPart,nombre1,apellido1,edad1,idgenero1,idPais1);
                    response.sendRedirect(request.getContextPath() + "/participantes");
                }else{
                    response.sendRedirect(request.getContextPath() + "/participantes?action=editar&er=1&id="+idPartStr);
                }

                break;
        }
    }
}
