package com.example.lab9.Daos;

import com.example.lab9.Beans.Continente;
import com.example.lab9.Beans.Pais;

import java.sql.*;
import java.util.ArrayList;

public class PaisDao extends BaseDao {

    public ArrayList<Pais> listaPais() {
        ArrayList<Pais> listapais = new ArrayList<Pais>();
        String sql = "SELECT p.idpais,p.nombre,p.poblacion,p.tamanio,p.idcontinente,c.nombre FROM pais p\n" +
                "inner join continente c on (p.idcontinente=c.idcontinente) order by p.nombre ASC;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pais pais = new Pais();
                Continente continente = new Continente();
                pais.setIdPais(rs.getInt(1));
                pais.setNombrePais(rs.getString(2));
                pais.setPoblacion(rs.getInt(3));
                pais.setTamanio(rs.getInt(4));
                pais.setIdContinente(rs.getInt(5));
                continente.setNombre(rs.getString(6));
                pais.setContinente(continente);
                listapais.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listapais;
    }

    public ArrayList<Pais> listaPaisxContinente(int idContinente) {

        ArrayList<Pais> listaPaisxContinente = new ArrayList<Pais>();

        String sql = "SELECT p.idpais,p.nombre,p.poblacion,p.tamanio,p.idcontinente,c.nombre FROM pais p \n" +
                "inner join continente c on p.idcontinente=c.idcontinente where c.idcontinente=? order by p.nombre ASC;";

        try (Connection conn = this.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, idContinente);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Continente continente = new Continente();
                    Pais pais = new Pais();
                    pais.setIdPais(rs.getInt(1));
                    pais.setNombrePais(rs.getString(2));
                    pais.setPoblacion(rs.getInt(3));
                    pais.setTamanio(rs.getInt(4));
                    pais.setIdContinente(rs.getInt(5));
                    continente.setNombre(rs.getString(6));
                    pais.setContinente(continente);
                    listaPaisxContinente.add(pais);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPaisxContinente;
    }

    public Pais obtenerPais(int idPais) {
        Pais pais = null;
        String sql = "SELECT * FROM pais WHERE idpais = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idPais);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pais = new Pais();
                    pais.setIdPais (rs.getInt(1));
                    pais.setNombrePais(rs.getString(2));
                    pais.setPoblacion(rs.getInt(3));
                    pais.setTamanio(rs.getInt(4));
                    pais.setIdContinente(rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }


    public void añadirPais(String nombre, int idContinente, int poblacion, int tamanio) {
        String sql = "INSERT INTO pais (nombre,poblacion,tamanio,idcontinente) VALUES (?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, poblacion);
            pstmt.setInt(3, tamanio);
            pstmt.setInt(4, idContinente);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarPais(String nombre, int idContinente, int poblacion, int tamanio, int idPais) {
        String sql = "UPDATE pais SET nombre=?,poblacion=?,tamanio=?,idcontinente=? WHERE idpais=?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, poblacion);
            pstmt.setInt(3, tamanio);
            pstmt.setInt(4, idContinente);
            pstmt.setInt(5, idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPais(int idPais) {

        String sql = "DELETE from pais WHERE idpais =?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPais);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Continente> listaContinentes(){
        ArrayList<Continente> listaContinente = new ArrayList<Continente>();
        String sql = "SELECT * FROM continente";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Continente continente = new Continente();
                continente.setIdContinente(rs.getInt(1));
                continente.setNombre(rs.getString(2));
                listaContinente.add(continente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaContinente;
    }

}
