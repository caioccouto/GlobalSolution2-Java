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
        ValidacaoBO.validarConsumo(e.getConsumoEstimadoLitros());
        ValidacaoBO.validarCarbonoEmitido(e.getCo2EmitidoKg());
        ValidacaoBO.validarImpactoAmbiental(e.getIndiceImpactoAmbiental());
        if (e.getViagemId() == null) {
            throw new IllegalArgumentException("Emissão deve estar vinculada a uma viagem!");
        }
        emissaoDAO.inserir(e);
    }
}