/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author qadri
 */
import Modelo.Group;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestión de grupos o categorías de contactos.
 */
public class GroupDB {

    /**
     * Agrega un nuevo grupo a la base de datos.
     * @param group Objeto Group con nombre y descripción.
     */
    public static void agregarGrupo(Group group) {
        String sql = "INSERT INTO grupos (nombre, descripcion) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, group.getNombre());
            stmt.setString(2, group.getDescripcion());
            stmt.executeUpdate();

        } catch (SQLException e) {
            ErrorLogger.log(e, "agregarGrupo");
        }
    }

    /**
     * Lista todos los grupos existentes en la base de datos.
     * @return Lista de objetos Group.
     */
    public static List<Group> listarGrupos() {
        List<Group> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupos";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("id_grupo"));
                g.setNombre(rs.getString("nombre"));
                g.setDescripcion(rs.getString("descripcion"));
                lista.add(g);
            }

        } catch (SQLException e) {
            ErrorLogger.log(e, "listarGrupos");
        }

        return lista;
    }
}
