package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.AlertaAmbiental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaAmbientalDAO {

    public void inserir(AlertaAmbiental a) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ALERTA_AMBIENTAL (TIPO, DESCRICAO, NIVEL, EMPRESA_ID) VALUES (?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, a.getTipo());
            ps.setString(2, a.getDescricao());
            ps.setString(3, a.getNivel());
            ps.setLong(4, a.getEmpresaId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getLong(1));
            }
        }
    }

    public List<AlertaAmbiental> selecionar() throws SQLException, ClassNotFoundException {
        List<AlertaAmbiental> lista = new ArrayList<>();
        String sql = "SELECT * FROM ALERTA_AMBIENTAL ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public AlertaAmbiental buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM ALERTA_AMBIENTAL WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private AlertaAmbiental mapear(ResultSet rs) throws SQLException {
        AlertaAmbiental a = new AlertaAmbiental();
        a.setId(rs.getLong("ID"));
        a.setTipo(rs.getString("TIPO"));
        a.setDescricao(rs.getString("DESCRICAO"));
        a.setNivel(rs.getString("NIVEL"));
        a.setEmpresaId(rs.getLong("EMPRESA_ID"));
        return a;
    }
}