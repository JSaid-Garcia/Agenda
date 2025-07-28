/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author qadri
 */
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase utilitaria para guardar errores en la base de datos.
 */
public class ErrorLogger {
    /**
     * Registra un error con el mensaje y el nombre del método en la tabla errores.
     * @param ex Excepción ocurrida.
     * @param metodo Método donde ocurrió el error.
     */
    public static void log(Exception ex, String metodo) {
        String sql = "INSERT INTO errores (mensaje, metodo) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, ex.getMessage());
            stmt.setString(2, metodo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar el log: " + e.getMessage());
        }
    }

    static void log(SQLException e, String agregarContacto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
