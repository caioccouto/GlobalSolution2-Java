package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Viagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {

    public void inserir(Viagem v) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO VIAGEM (DATA_VIAGEM, CARGA_TRANSPORTADA_KG, DISTANCIA_PERCORRIDA_KM, CAMINHAO_ID, MOTORISTA_ID, ROTA_ID, COMBUSTIVEL_ID) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setDate(1, Date.valueOf(v.getDtViagem()));
            ps.setDouble(2, v.getCargaTransportadaKg());
            ps.setDouble(3, v.getDistanciaPercorridaKm());
            ps.setLong(4, v.getCaminhaoId());
            ps.setLong(5, v.getMotoristaId());
            ps.setLong(6, v.getRotaId());
            ps.setLong(7, v.getCombustivelId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) v.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Viagem v) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE VIAGEM SET DATA_VIAGEM=?, CARGA_TRANSPORTADA_KG=?, DISTANCIA_PERCORRIDA_KM=?, CAMINHAO_ID=?, MOTORISTA_ID=?, ROTA_ID=?, COMBUSTIVEL_ID=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(v.getDtViagem()));
            ps.setDouble(2, v.getCargaTransportadaKg());
            ps.setDouble(3, v.getDistanciaPercorridaKm());
            ps.setLong(4, v.getCaminhaoId());
            ps.setLong(5, v.getMotoristaId());
            ps.setLong(6, v.getRotaId());
            ps.setLong(7, v.getCombustivelId());
            ps.setLong(8, v.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM VIAGEM WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Viagem> selecionar() throws SQLException, ClassNotFoundException {
        List<Viagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM VIAGEM ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Viagem buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM VIAGEM WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Viagem mapear(ResultSet rs) throws SQLException {
        Viagem v = new Viagem();
        v.setId(rs.getLong("ID"));
        v.setDtViagem(rs.getDate("DATA_VIAGEM").toLocalDate());
        v.setCargaTransportadaKg(rs.getDouble("CARGA_TRANSPORTADA_KG"));
        v.setDistanciaPercorridaKm(rs.getDouble("DISTANCIA_PERCORRIDA_KM"));
        v.setCaminhaoId(rs.getLong("CAMINHAO_ID"));
        v.setMotoristaId(rs.getLong("MOTORISTA_ID"));
        v.setRotaId(rs.getLong("ROTA_ID"));
        v.setCombustivelId(rs.getLong("COMBUSTIVEL_ID"));
        return v;
    }
}