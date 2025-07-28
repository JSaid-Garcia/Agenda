/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author qadri
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que administra la conexión a SQL Server usando JDBC.
 */
public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Miagendabd;encrypt=false";
    private static final String USER = "WISTON_JOSE"; // Cambia esto por tu usuario SQL
    private static final String PASSWORD = "123456"; // Cambia por tu contraseña

    /**
     * Obtiene una conexión activa a la base de datos.
     * @return Objeto Connection conectado a SQL Server.
     * @throws SQLException Si ocurre un error en la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
