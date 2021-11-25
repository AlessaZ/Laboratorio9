package com.example.lab9.Daos;
import com.example.lab9.Beans.*;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDao extends BaseDao{

    public ArrayList<Estudiante> listaEstudianteFiltrada(int idUniversidad){
        ArrayList<Estudiante> listaEstudiante = new ArrayList<Estudiante>();
        String sql = "SELECT e.idestudiante,p.nombre,p.apellido,p.edad,e.codigo,e.promedio,c.nombre FROM estudiante e\n" +
                "inner join participante p on (p.idparticipante=e.idparticipante)\n" +
                "inner join condicion c on(e.idcondicion=c.idcondicion) where e.iduniversidad=? order by e.promedio;";

        try (Connection conn = this.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, idUniversidad);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Estudiante estudiante = new Estudiante();
                    Condicion condicion=new Condicion();
                    estudiante.setIdEstudiante(rs.getInt(1));
                    estudiante.setNombreParticipante(rs.getString(2));
                    estudiante.setApellidoParticipante(rs.getString(3));
                    estudiante.setEdad(rs.getInt(4));
                    estudiante.setCodigoAlumno(rs.getString(5));
                    estudiante.setPromedio(rs.getDouble(6));
                    condicion.setNombre(rs.getString(7));
                    estudiante.setCondicion(condicion);
                    listaEstudiante.add(estudiante);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEstudiante;
    }

    public ArrayList<Participante> listaPartNotInEst(){

        ArrayList<Participante> listaPart = new ArrayList<Participante>();
        String sql = "SELECT p.idparticipante FROM participante p\n" +
                "where p.idparticipante not in  (select e.idparticipante from estudiante e);";

        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                Participante participante=new Participante();
                participante.setIdParticipante(rs.getInt(1));
                listaPart.add(participante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPart;
    }


    public void eliminarLogEstudiante(int idEst){
        String sql = "UPDATE estudiante SET idcondicion=1 WHERE idestudiante=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEst);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEstudiante(int idEst) {
         String sql = "DELETE from estudiante where idestudiante=?;";
         try (Connection conn = this.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, idEst);
             pstmt.executeUpdate();

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

    public void añadirEstudiante(String codigo, double promedio,int idparticipante, int iduniversidad) {
        String sql = "insert into estudiante (codigo,promedio,idcondicion,idparticipante,iduniversidad) values (?,?,2,?,?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setDouble(2, promedio);
            pstmt.setInt(3,idparticipante);
            pstmt.setInt(4, iduniversidad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEstudiantesP(int idPais) {
        String sql = "DELETE from estudiante WHERE idpais=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //SE GENERÓ UN BUSCADOR DE ID SOLO POR NOMBRE Y APELLIDO, POR LO QUE NO ESTÁ CONTEMPLADO QUE NOMBRE Y APELLIDO SEAN IGUALES
    public int buscarId(String nombreEst, String apellidoEst){

        int idParticipante=0;
        ParticipanteDao participanteDao=new ParticipanteDao();

        ArrayList<Participante> listaParticipantes=participanteDao.listaParticipante();
        ArrayList<Participante> listaPartFiltrada=this.listaPartNotInEst();

        for(Participante participante : listaParticipantes){
            if(nombreEst.equalsIgnoreCase(participante.getNombreParticipante())){
                if(apellidoEst.equalsIgnoreCase(participante.getApellidoParticipante())){
                    for(Participante participante1: listaPartFiltrada){
                        if(participante.getIdParticipante()==participante1.getIdParticipante()){
                            idParticipante=participante1.getIdParticipante();
                            break;
                        }
                    }
                }else{
                    System.out.println("No encuentra el apellido");
                    break;
                }
            }
        }
        return idParticipante;
    }


   public Estudiante obtenerEstudiante(int idEstudiante){

    Estudiante est = null;

    String sql = "SELECT e.idestudiante,pe.nombre, pe.apellido, e.codigo,e.promedio,c.nombre,u.nombre,u.iduniversidad,p.idpais FROM estudiante e inner join  participante pe on (pe.idparticipante = e.idparticipante)\n" +
            "inner join pais p on (p.idpais = pe.idpais)\n" +
            "inner join condicion c on (c.idcondicion = e.idcondicion)\n" +
            "inner join universidad u on (u.iduniversidad = e.iduniversidad) where idestudiante=?;";

    try (Connection conn = this.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idEstudiante);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                est = new Estudiante();
                Condicion condicion=new Condicion();
                Pais pais=new Pais();
                Universidad universidad=new Universidad();
                est.setIdEstudiante(rs.getInt(1));
                est.setNombreParticipante(rs.getString(2));
                est.setApellidoParticipante(rs.getString(3));
                est.setCodigoAlumno(rs.getString(4));
                est.setPromedio(rs.getDouble(5));
                condicion.setNombre(rs.getString(6));
                est.setCondicion(condicion);
                universidad.setNombreUniversidad(rs.getString(7));
                universidad.setIdUniversidad(rs.getInt(8));
                pais.setIdPais(rs.getInt(9));
                est.setPais(pais);
                est.setUniversidad(universidad);

            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return est;
  }

    public void editarEst(String codigo, double promedio,int iduniversidad,int idEst) {
        String sql = "UPDATE estudiante SET codigo=?,promedio=?,iduniversidad=? WHERE idestudiante=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setDouble(2, promedio);
            pstmt.setInt(3, iduniversidad);
            pstmt.setInt(4, idEst);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
