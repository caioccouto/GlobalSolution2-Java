package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    public void inserir(Empresa e) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO EMPRESA (NOME, CNPJ, SETOR, UF, CIDADE, RESPONSAVEL, DATA_CADASTRO, META_CONSUMO) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getCnpj().replaceAll("[^0-9]", ""));
            ps.setString(3, e.getSetor());
            ps.setString(4, e.getUf());
            ps.setString(5, e.getCidade());
            ps.setString(6, e.getResponsavel());
            ps.setDate(7, Date.valueOf(e.getDtCadastro()));
            ps.setDouble(8, e.getMetaConsumo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Empresa e) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE EMPRESA SET NOME=?, SETOR=?, UF=?, CIDADE=?, RESPONSAVEL=?, META_CONSUMO=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getSetor());
            ps.setString(3, e.getUf());
            ps.setString(4, e.getCidade());
            ps.setString(5, e.getResponsavel());
            ps.setDouble(6, e.getMetaConsumo());
            ps.setLong(7, e.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM EMPRESA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Empresa> selecionar() throws SQLException, ClassNotFoundException {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESA ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Empresa buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM EMPRESA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Empresa mapear(ResultSet rs) throws SQLException {
        Empresa e = new Empresa();
        e.setId(rs.getLong("ID"));
        e.setNome(rs.getString("NOME"));
        e.setCnpj(rs.getString("CNPJ"));
        e.setSetor(rs.getString("SETOR"));
        e.setUf(rs.getString("UF"));
        e.setCidade(rs.getString("CIDADE"));
        e.setResponsavel(rs.getString("RESPONSAVEL"));
        e.setDtCadastro(rs.getDate("DATA_CADASTRO").toLocalDate());
        e.setMetaConsumo(rs.getDouble("META_CONSUMO"));
        return e;
    }
}
