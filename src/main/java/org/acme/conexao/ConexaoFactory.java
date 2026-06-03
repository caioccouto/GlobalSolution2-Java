package org.acme.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String user = "rm563452";
        String pwd = "260506";

        try{
            return DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
