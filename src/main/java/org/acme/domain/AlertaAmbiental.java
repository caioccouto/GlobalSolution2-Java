package org.acme.domain;

import java.time.LocalDate;

public class AlertaAmbiental {
    private Long id;
    private String tipo;
    private String descricao;
    private String nivel;
    private Long empresaId;

    public AlertaAmbiental() {}

    public AlertaAmbiental(String tipo, String descricao, String nivel, Long empresaId) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.empresaId = empresaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}