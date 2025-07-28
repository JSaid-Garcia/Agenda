/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author qadri
 */

import Modelo.Contact;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDB {

    public static void agregarContacto(Contact contacto) {
        String sql = "INSERT INTO contactos (nombre, apellidos, telefono, email, direccion, etiquetas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getApellidos());
            stmt.setString(3, contacto.getTelefono());
            stmt.setString(4, contacto.getEmail());
            stmt.setString(5, contacto.getDireccion());
            stmt.setString(6, contacto.getEtiquetas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            DAO.ErrorLogger.log(e, "agregarContacto");
        }
    }

    public static List<Contact> listarContactos() {
        List<Contact> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos";
        try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("Id_Contacto"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setDireccion(rs.getString("direccion"));
                c.setEtiquetas(rs.getString("etiquetas"));
                lista.add(c);
            }
        } catch (SQLException e) {
            DAO.ErrorLogger.log(e, "listarContactos");
        }
        return lista;
    }

    // Agrega métodos actualizarContacto, eliminarContacto si los necesitas
}
