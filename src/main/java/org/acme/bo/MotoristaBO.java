package org.acme.bo;

import org.acme.dao.MotoristaDAO;
import org.acme.domain.Motorista;

import java.sql.SQLException;
import java.util.List;

public class MotoristaBO {

    private final MotoristaDAO motoristaDAO = new MotoristaDAO();

    public List<Motorista> listarBo() throws SQLException, ClassNotFoundException {
        return motoristaDAO.selecionar();
    }

    public Motorista buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return motoristaDAO.buscarPorId(id);
    }

    public void inserirBo(Motorista m) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(m.getNome());
        ValidacaoBO.validarCpf(m.getCpf());
        ValidacaoBO.validarValidadeCnh(m.getValidadeCnh());
        motoristaDAO.inserir(m);
    }

    public void atualizarBo(Motorista m) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(m.getNome());
        ValidacaoBO.validarValidadeCnh(m.getValidadeCnh());
        motoristaDAO.atualizar(m);
    }

    public void deletarBo(Long id) throws SQLException, ClassNotFoundException {
        motoristaDAO.deletar(id);
    }
}