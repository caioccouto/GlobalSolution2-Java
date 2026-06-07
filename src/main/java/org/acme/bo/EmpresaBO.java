package org.acme.bo;

import org.acme.dao.EmpresaDAO;
import org.acme.domain.Empresa;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmpresaBO {

    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    public List<Empresa> listarBo() throws SQLException, ClassNotFoundException {
        return empresaDAO.selecionar();
    }

    public Empresa buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return empresaDAO.buscarPorId(id);
    }

    public void inserirBo(Empresa e) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(e.getNome());
        ValidacaoBO.validarCnpj(e.getCnpj());
        if (e.getDtCadastro() == null) {
            e.setDtCadastro(LocalDate.now());
        }
        ValidacaoBO.validarMetaConsumo(e.getMetaConsumo());
        empresaDAO.inserir(e);
    }

    public void atualizarBo(Empresa e) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(e.getNome());
        ValidacaoBO.validarMetaConsumo(e.getMetaConsumo());
        empresaDAO.atualizar(e);
    }

    public void deletarBo(Long id) throws SQLException, ClassNotFoundException {
        empresaDAO.deletar(id);
    }
}
