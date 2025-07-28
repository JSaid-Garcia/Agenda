/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class insertarEventoBD {
    

   public void insertar(String nombre, String apellido, String email, String direccion, String etiqueta) {
        String sql = "INSERT INTO Contactos (Nombre, Apellido, Email, Direccion, Etiqueta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, direccion);
            ps.setString(5, etiqueta);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Contacto insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el contacto.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}