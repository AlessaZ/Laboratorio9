package com.example.lab9.Daos;
import com.example.lab9.Beans.Continente;
import com.example.lab9.Beans.Pais;
import com.example.lab9.Beans.Universidad;

import java.sql.*;
import java.util.ArrayList;

public class UniversidadesDao extends BaseDao{

    public ArrayList<Universidad> listaUniversidad(){
        ArrayList<Universidad> listauniversidad = new ArrayList<Universidad>();

        String sql="SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,p.idcontinente FROM universidad u inner join pais p on (u.idpais = p.idpais) order by u.ranking;";

        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                Universidad uni = new Universidad();
                Pais pais=new Pais();
                uni.setIdUniversidad(rs.getInt(1));
                uni.setNombreUniversidad(rs.getString(2));
                uni.setRanking(rs.getInt(3));
                uni.setNumAlumnos(rs.getInt(4));
                uni.setFotoUniversidad(rs.getString(5));
                pais.setNombrePais(rs.getString(6));
                pais.setIdContinente(rs.getInt(7));
                uni.setPais(pais);
                listauniversidad.add(uni);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listauniversidad;
    }

    public ArrayList<Universidad> listaUniversidadesOrder(String orden){

        ArrayList<Universidad> listaUniOrder = new  ArrayList<Universidad>();
        String sql="SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,p.idcontinente FROM universidad u inner join pais p on (u.idpais = p.idpais) order by u.ranking";
        
        switch (orden) {
            case "numAlumnos":
                sql = "SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,p.idcontinente FROM universidad u inner join pais p on (u.idpais = p.idpais) order by u.numAlumnos desc ";
                break;
            case "nombreU":
                sql = "SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,p.idcontinente FROM universidad u inner join pais p on (u.idpais = p.idpais) order by u.nombre";
                break;
            case "pais":
                sql = "SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,p.idcontinente FROM universidad u inner join pais p on (u.idpais = p.idpais) order by p.nombre";
                break;
        }

        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                Universidad uni = new Universidad();
                Pais pais=new Pais();
                uni.setIdUniversidad(rs.getInt(1));
                uni.setNombreUniversidad(rs.getString(2));
                uni.setRanking(rs.getInt(3));
                uni.setNumAlumnos(rs.getInt(4));
                uni.setFotoUniversidad(rs.getString(5));
                pais.setNombrePais(rs.getString(6));
                pais.setIdContinente(rs.getInt(7));
                uni.setPais(pais);
                listaUniOrder.add(uni);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaUniOrder;
    }


    public void añadirUniversidad(String nombre, int ranking, String foto, int idPais) {

        String sql = "INSERT INTO universidad (nombre,ranking,foto,idpais) VALUES (?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, ranking);
            pstmt.setString(3, foto);
            pstmt.setInt(4,idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Se puede editar la universidad, excepto numero de alumnos
    public void editarUniversidad(int idUni,String nombre, int ranking, String foto, int idPais) {
        String sql = "UPDATE universidad SET nombre=?,ranking=?,foto=?,idpais=? WHERE iduniversidad=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, ranking);
            pstmt.setString(3, foto);
            pstmt.setInt(4, idPais);
            pstmt.setInt(5, idUni);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarUniversidad(int idUniversidad) {

        String sql = "DELETE from universidad WHERE iduniversidad=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUniversidad);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarEstudiantesU(int idUniversidad) {

        String sql = "DELETE from estudiante WHERE iduniversidad=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUniversidad);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Universidad> listaUniversidadxPais(int idPais) {

        ArrayList<Universidad> listaUniversidadxPais = new ArrayList<Universidad>();

        String sql = "SELECT u.iduniversidad, u.nombre, u.ranking, u.numAlumnos, u.foto, p.nombre FROM universidad u \n" +
                "inner join pais p on (p.idpais = u.idpais) where p.idpais = ? order by u.nombre ASC";

        try (Connection conn = this.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, idPais);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Pais pais = new Pais();
                    Universidad uni = new Universidad();
                    uni.setIdUniversidad(rs.getInt(1));
                    uni.setNombreUniversidad(rs.getString(2));
                    uni.setRanking(rs.getInt(3));
                    uni.setNumAlumnos(rs.getInt(4));
                    uni.setFotoUniversidad(rs.getString(5));
                    pais.setNombrePais(rs.getString(6));
                    uni.setPais(pais);
                    listaUniversidadxPais.add(uni);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUniversidadxPais;
    }

    public boolean paisHasU(int idPais){
        boolean has = false;
        ArrayList<Universidad> listaUniversidadxPais = listaUniversidadxPais(idPais);
        if (listaUniversidadxPais.isEmpty()){
            has = true;
        }
        return has;
    }


    public Universidad obtenerUniversidad(int idUniversidad){

        Universidad uni = null;

        String sql = "SELECT u.iduniversidad,u.nombre,u.ranking,u.numAlumnos,u.foto,p.nombre,u.idpais FROM universidad u\n" +
                " inner join pais p on (p.idpais = u.idpais)\n" +
                " where u.iduniversidad = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUniversidad);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    uni = new Universidad();
                    Pais pais = new Pais();
                    uni.setIdUniversidad(rs.getInt(1));
                    uni.setNombreUniversidad(rs.getString(2));
                    uni.setRanking(rs.getInt(3));
                    uni.setNumAlumnos(rs.getInt(4));
                    uni.setFotoUniversidad(rs.getString(5));
                    pais.setNombrePais(rs.getString(6));
                    pais.setIdPais(rs.getInt(7));
                    uni.setPais(pais);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uni;
    }

    public boolean universidadIsLista(String nombreU){
        ArrayList<Universidad> listaUniversidad = listaUniversidad();

        boolean noLista = true;

        for(Universidad uni : listaUniversidad){
            if(uni.getNombreUniversidad().equalsIgnoreCase(nombreU)) {
                noLista = false;
                break;
            }
        }
        return noLista;
    }

}
