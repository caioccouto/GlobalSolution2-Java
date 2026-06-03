package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Motorista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    public void inserir(Motorista m) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO MOTORISTA (NOME, CPF, NUMERO_CNH, VALIDADE_CNH, EMPRESA_ID) VALUES (?,?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCpf().replaceAll("[^0-9]", ""));
            ps.setString(3, m.getNumeroCnh());
            ps.setDate(4, Date.valueOf(m.getValidadeCnh()));
            ps.setLong(5, m.getEmpresaId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Motorista m) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE MOTORISTA SET NOME=?, NUMERO_CNH=?, VALIDADE_CNH=?, EMPRESA_ID=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getNumeroCnh());
            ps.setDate(3, Date.valueOf(m.getValidadeCnh()));
            ps.setLong(4, m.getEmpresaId());
            ps.setLong(5, m.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM MOTORISTA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Motorista> selecionar() throws SQLException, ClassNotFoundException {
        List<Motorista> lista = new ArrayList<>();
        String sql = "SELECT * FROM MOTORISTA ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Motorista buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM MOTORISTA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Motorista mapear(ResultSet rs) throws SQLException {
        Motorista m = new Motorista();
        m.setId(rs.getLong("ID"));
        m.setNome(rs.getString("NOME"));
        m.setCpf(rs.getString("CPF"));
        m.setNumeroCnh(rs.getString("NUMERO_CNH"));
        m.setValidadeCnh(rs.getDate("VALIDADE_CNH").toLocalDate());
        m.setEmpresaId(rs.getLong("EMPRESA_ID"));
        return m;
    }
}