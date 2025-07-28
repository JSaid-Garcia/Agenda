/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author qadri
 */
import Modelo.Event;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para manejar operaciones con la tabla eventos.
 */
public class EventDB {

    /**
     * Inserta un nuevo evento en la base de datos.
     * @param event Objeto Event con los datos del evento.
     */
    public static void agregarEvento(Event event) {
        String sql = "INSERT INTO eventos (Id_Contacto, fecha, hora, descripcion, ubicacion) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, event.getIdContacto());
            stmt.setDate(2, Date.valueOf(event.getFecha()));
            stmt.setTime(3, Time.valueOf(event.getHora()));
            stmt.setString(4, event.getDescripcion());
            stmt.setString(5, event.getUbicacion());

            stmt.executeUpdate();

        } catch (SQLException e) {
            ErrorLogger.log(e, "agregarEvento");
        }
    }

    /**
     * Retorna una lista con todos los eventos registrados.
     * @return Lista de objetos Event.
     */
    public static List<Event> listarEventos() {
        List<Event> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("Id_Evento"));
                e.setIdContacto(rs.getInt("Id_Contacto"));
                e.setFecha(rs.getDate("fecha").toLocalDate());
                e.setHora(rs.getTime("hora").toLocalTime());
                e.setDescripcion(rs.getString("descripcion"));
                e.setUbicacion(rs.getString("ubicacion"));

                lista.add(e);
            }

        } catch (SQLException e) {
            ErrorLogger.log(e, "listarEventos");
        }

        return lista;
    }

    // Puedes agregar métodos actualizarEvento y eliminarEvento si lo deseas.
}
