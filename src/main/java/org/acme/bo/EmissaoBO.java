package org.acme.bo;

import org.acme.dao.EmissaoDAO;
import org.acme.domain.Emissao;

import java.sql.SQLException;
import java.util.List;

public class EmissaoBO {

    private final EmissaoDAO emissaoDAO = new EmissaoDAO();

    public List<Emissao> listarBo() throws SQLException, ClassNotFoundException {
        return emissaoDAO.selecionar();
    }

    public Emissao buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return emissaoDAO.buscarPorId(id);
    }

    public void inserirBo(Emissao e) throws SQLException, ClassNotFoundException {
        if (e.getViagemId() == null) {
            throw new IllegalArgumentException("Emissão deve estar vinculada a uma viagem!");
        }
        if (e.getConsumoEstimadoLitros() == null || e.getConsumoEstimadoLitros() <= 0) {
            throw new IllegalArgumentException("Consumo estimado deve ser maior que zero!");
        }
        if (e.getCo2EmitidoKg() == null || e.getCo2EmitidoKg() <= 0) {
            throw new IllegalArgumentException("CO2 emitido deve ser maior que zero!");
        }
        if (e.getIndiceImpactoAmbiental() == null || e.getIndiceImpactoAmbiental().trim().isEmpty()) {
            throw new IllegalArgumentException("Índice de impacto ambiental não pode ser nulo!");
        }
        emissaoDAO.inserir(e);
    }
}