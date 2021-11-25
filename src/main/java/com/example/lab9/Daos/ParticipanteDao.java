package com.example.lab9.Daos;
import com.example.lab9.Beans.Genero;
import com.example.lab9.Beans.Pais;
import com.example.lab9.Beans.Participante;
import com.example.lab9.DTO.PaisMayorNumParticipantesDto;
import com.example.lab9.DTO.PorcentajeHyMDto;
import com.example.lab9.DTO.PromEdadPartDto;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ParticipanteDao extends BaseDao {

    public ArrayList<Participante> listaParticipante(){
        ArrayList<Participante> listaParticipante= new ArrayList<Participante>();
        String sql="SELECT p.idparticipante,p.nombre,p.apellido,p.edad,pa.nombre, g.nombre FROM participante p \n" +
                "inner join genero g on (p.idgenero=g.idgenero)\n" +
                "inner join pais pa on (p.idpais=pa.idpais) order by p.idparticipante;";
        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                Participante participante= new Participante();
                Pais pais=new Pais();
                Genero genero=new Genero();
                participante.setIdParticipante(rs.getInt(1));
                participante.setNombreParticipante(rs.getString(2));
                participante.setApellidoParticipante(rs.getString(3));
                participante.setEdad(rs.getInt(4));
                pais.setNombrePais(rs.getString(5));
                participante.setPais(pais);
                genero.setGenero(rs.getString(6));
                participante.setGenero(genero);
                listaParticipante.add(participante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaParticipante;
    }

    public Participante obtenerParticipante(int idParticipante){
        Participante participante=null;
        String sql="SELECT p.idparticipante,p.nombre,p.apellido,p.edad,pa.nombre, g.nombre,p.idpais,g.idgenero FROM participante p \n" +
                "inner join genero g on (p.idgenero=g.idgenero)\n" +
                "inner join pais pa on (p.idpais=pa.idpais) where p.idparticipante=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idParticipante);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    participante= new Participante();
                    Pais pais=new Pais();
                    Genero genero=new Genero();
                    participante.setIdParticipante(rs.getInt(1));
                    participante.setNombreParticipante(rs.getString(2));
                    participante.setApellidoParticipante(rs.getString(3));
                    participante.setEdad(rs.getInt(4));
                    pais.setNombrePais(rs.getString(5));
                    genero.setGenero(rs.getString(6));
                    pais.setIdPais(rs.getInt(7));
                    genero.setIdGenero(rs.getInt(8));
                    participante.setPais(pais);
                    participante.setGenero(genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  participante;
    }

    public void añadirParticipante(String nombre, String apellido, int edad,int idGenero, int idPais) {
        String sql = "INSERT INTO participante (nombre,apellido,edad,idgenero,idpais) VALUES (?,?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setInt(3, edad);
            pstmt.setInt(4, idGenero);
            pstmt.setInt(5, idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarParticipante(int idParticipante,String nombre, String apellido, int edad,int idGenero, int idPais) {
        String sql ="UPDATE participante SET nombre=?,apellido=?,edad=?,idgenero=?,idpais=? WHERE (idparticipante=?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setInt(3, edad);
            pstmt.setInt(4, idGenero);
            pstmt.setInt(5, idPais);
            pstmt.setInt(6,idParticipante);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarParticipante(int idParticipante) {
        String sql = "DELETE from participante WHERE idparticipante=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idParticipante);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PaisMayorNumParticipantesDto> paisNumPart(){

        ArrayList<PaisMayorNumParticipantesDto> listaPaisNumPart=new ArrayList<PaisMayorNumParticipantesDto>();
        PaisMayorNumParticipantesDto paisNumPart=new PaisMayorNumParticipantesDto();
        String sql="SELECT *\n" +
                "FROM (SELECT pa.nombre, count(p.idparticipante) cantidad from participante p \n" +
                "inner join pais pa on (p.idpais=pa.idpais) group by pa.idpais) AS T1\n" +
                "WHERE cantidad IN (SELECT MAX(cantidad)\n" +
                "FROM (SELECT pa.nombre, count(p.idparticipante) cantidad from participante p inner join pais pa on (p.idpais=pa.idpais) group by pa.idpais) AS T2)";
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
               paisNumPart.setNombrePais(rs.getString(1));
               paisNumPart.setNumParticipantes(rs.getInt(2));
               listaPaisNumPart.add(paisNumPart);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaPaisNumPart;
    }

    public ArrayList<PorcentajeHyMDto> percentHyM(){

        ArrayList<PorcentajeHyMDto> percentHyM = new ArrayList<PorcentajeHyMDto>();

        String sql = "SELECT g.nombre, count(p.idparticipante)*100/(select count(idparticipante) from participante) \n" +
                "FROM genero g left join participante p on (p.idgenero=g.idgenero) group by p.idgenero;";

        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
                PorcentajeHyMDto percent = new PorcentajeHyMDto();
                percent.setNombreSexo(rs.getString(1));
                percent.setPorcentaje((double)Math.round(rs.getDouble(2) * 100d) / 100d);
                percentHyM.add(percent);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return percentHyM;
    }

    public PromEdadPartDto promedioEdad(){

        PromEdadPartDto promedioEdad=new PromEdadPartDto();
        String sql="SELECT avg(edad) from participante;";
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            if (rs.next()) {
                promedioEdad.setPromedio(rs.getDouble(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return promedioEdad;
    }

    public ArrayList<Genero> listaGenero(){
        ArrayList<Genero> listaGenero= new ArrayList<Genero>();
        String sql="SELECT * from genero;";
        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                Genero genero=new Genero();
                genero.setIdGenero(rs.getInt(1));
                genero.setGenero(rs.getString(2));
                listaGenero.add(genero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaGenero;
    }


    public boolean esString(String frase ){
        boolean esString=false;
        String abecedario="abcdefghijklmnñopqrstuvwxyz";
        frase=frase.toLowerCase();
        char letraFrase=frase.charAt(0);

        for(int i=0;i<abecedario.length();i++){
            char letraAbeced=abecedario.charAt(i);
            //
            if(letraAbeced==letraFrase){
                esString=true;
                break;
            }
        }
        return esString;
    }

}
