package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Caminhao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoDAO {

    public void inserir(Caminhao c) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO CAMINHAO (PLACA, MODELO, ANO_FABRICACAO, CAPACIDADE_CARGA, EMPRESA_ID) VALUES (?,?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, c.getPlaca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAnoFabricacao());
            ps.setDouble(4, c.getCapacidadeCarga());
            ps.setLong(5, c.getEmpresaId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Caminhao c) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE CAMINHAO SET PLACA=?, MODELO=?, ANO_FABRICACAO=?, CAPACIDADE_CARGA=?, EMPRESA_ID=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getPlaca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAnoFabricacao());
            ps.setDouble(4, c.getCapacidadeCarga());
            ps.setLong(5, c.getEmpresaId());
            ps.setLong(6, c.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM CAMINHAO WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Caminhao> selecionar() throws SQLException, ClassNotFoundException {
        List<Caminhao> lista = new ArrayList<>();
        String sql = "SELECT * FROM CAMINHAO ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Caminhao buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM CAMINHAO WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Caminhao mapear(ResultSet rs) throws SQLException {
        Caminhao c = new Caminhao();
        c.setId(rs.getLong("ID"));
        c.setPlaca(rs.getString("PLACA"));
        c.setModelo(rs.getString("MODELO"));
        c.setAnoFabricacao(rs.getInt("ANO_FABRICACAO"));
        c.setCapacidadeCarga(rs.getDouble("CAPACIDADE_CARGA"));
        c.setEmpresaId(rs.getLong("EMPRESA_ID"));
        return c;
    }
}
