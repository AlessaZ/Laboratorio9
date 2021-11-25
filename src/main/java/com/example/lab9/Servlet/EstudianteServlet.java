package com.example.lab9.Servlet;

import com.example.lab9.Beans.Estudiante;
import com.example.lab9.Beans.Participante;
import com.example.lab9.Beans.Universidad;
import com.example.lab9.Daos.EstudianteDao;
import com.example.lab9.Daos.ParticipanteDao;
import com.example.lab9.Daos.UniversidadesDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EstudianteServlet", value = "/estudiantes")
public class EstudianteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        RequestDispatcher view;
        EstudianteDao estudianteDao = new EstudianteDao();
        UniversidadesDao universidadesDao = new UniversidadesDao();
        switch (action) {
            case "añadir":
                String idUniStr = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                int idUni = Integer.parseInt(idUniStr);
                request.setAttribute("idUni", idUni);
                view = request.getRequestDispatcher("añadirEstudiante.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idUniStr1 = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                int idUni1 = Integer.parseInt(idUniStr1);
                String idPaisStr = request.getParameter("idPais") != null ? request.getParameter("idPais") : "";
                int idPais = Integer.parseInt(idPaisStr);
                request.setAttribute("idUni", idUni1);
                request.setAttribute("listaUniversidad",universidadesDao.listaUniversidadxPais(idPais));
                String idEstudianteStr = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idEst=Integer.parseInt(idEstudianteStr);
                Estudiante estudiante=estudianteDao.obtenerEstudiante(idEst);
                if (estudiante== null){
                    response.sendRedirect(request.getContextPath() + "/estudiantes");
                } else {
                    request.setAttribute("estudiante",estudiante);
                    view = request.getRequestDispatcher("editarEstudiante.jsp");
                    view.forward(request, response);
                }
                break;
            case "eliminar":
                String idEstudianteStr1 = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idEst2 = Integer.parseInt(idEstudianteStr1);
                Estudiante estudiante1=estudianteDao.obtenerEstudiante(idEst2);
                if (estudiante1 != null){
                    System.out.println("Entró");
                    estudianteDao.eliminarLogEstudiante(idEst2);
                }
                response.sendRedirect(request.getContextPath() + "/universidades?action=verEst&idUni=" + estudiante1.getUniversidad().getIdUniversidad());
                break;
            case "borrar":
                //Borrado de la base de datos
                String idEstudianteStr2 = request.getParameter("id") != null ? request.getParameter("id") : "";
                int idEst3 = Integer.parseInt(idEstudianteStr2);
                Estudiante estudiante2=estudianteDao.obtenerEstudiante(idEst3);
                if (estudiante2 != null){
                    estudianteDao.eliminarEstudiante(idEst3);
                }
                response.sendRedirect(request.getContextPath() + "/universidades?action=verEst&idUni=" + estudiante2.getUniversidad().getIdUniversidad());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EstudianteDao estudianteDao = new EstudianteDao();
        UniversidadesDao universidadesDao = new UniversidadesDao();
        ParticipanteDao participanteDao = new ParticipanteDao();
        String action = request.getParameter("action") != null ? request.getParameter("action") : "crear";
        switch (action) {
            case "crear":
                String nombre = request.getParameter("nombreEst") != null ? request.getParameter("nombreEst") : "";
                String apellido = request.getParameter("apellidoEst") != null ? request.getParameter("apellidoEst") : "";
                String codigo = request.getParameter("codigo") != null ? request.getParameter("codigo") : "";
                String promedioStr = request.getParameter("promedio") != null ? request.getParameter("promedio") : "";
                String idUniStr = request.getParameter("idUni") != null ? request.getParameter("idUni") : "";
                Double promedio = Double.parseDouble(promedioStr);
                int idUni = Integer.parseInt(idUniStr);
                int idPart = estudianteDao.buscarId(nombre, apellido);
                Universidad universidad = universidadesDao.obtenerUniversidad(idUni);
                Participante participante = participanteDao.obtenerParticipante(idPart);
                if (idPart != 0) {
                    if (universidad.getPais().getIdPais() == participante.getPais().getIdPais()) {
                        estudianteDao.añadirEstudiante(codigo, promedio, idPart, idUni);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/universidades?action=verEst&idUni=" + idUni);
                break;
           case "actualizar":
                String idEstStr = request.getParameter("idEst") != null ? request.getParameter("idEst") : "";
                String nombre1 = request.getParameter("nombreEst") != null ? request.getParameter("nombreEst") : "";
                String apellido1= request.getParameter("apellidoEst") != null ? request.getParameter("apellidoEst") : "";
                String codigo1 = request.getParameter("codigo")  != null ? request.getParameter("codigo"): "";
                String promedioStr1 = request.getParameter("promedio")  != null ? request.getParameter("promedio"): "";
                String idUniStr1 = request.getParameter("idUni")  != null ? request.getParameter("idUni"): "";
                Double promedio1 = Double.parseDouble(promedioStr1);
                int idUni1 = Integer.parseInt(idUniStr1);
                int idPart1=estudianteDao.buscarId(nombre1,apellido1);
                int idEst=Integer.parseInt(idEstStr);

                if(idPart1!=0){
                    estudianteDao.editarEst(codigo1,promedio1,idUni1,idEst);
                    System.out.println("Editó");
                }
                response.sendRedirect(request.getContextPath() + "/universidades?action=verEst&idUni="+idUni1);
                break;
        }
    }
}

