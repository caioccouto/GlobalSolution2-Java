package org.acme.domain;

import java.time.LocalDate;

public class Emissao {
    private Long id;
    private Long viagemId;
    private Double consumoEstimadoLitros;
    private Double co2EmitidoKg;
    private String indiceImpactoAmbiental;

    public Emissao() {}

    public Emissao(Long viagemId, Double consumoEstimadoLitros, Double co2EmitidoKg,
                   String indiceImpactoAmbiental) {
        this.viagemId = viagemId;
        this.consumoEstimadoLitros = consumoEstimadoLitros;
        this.co2EmitidoKg = co2EmitidoKg;
        this.indiceImpactoAmbiental = indiceImpactoAmbiental;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getViagemId() { return viagemId; }
    public void setViagemId(Long viagemId) { this.viagemId = viagemId; }

    public Double getConsumoEstimadoLitros() { return consumoEstimadoLitros; }
    public void setConsumoEstimadoLitros(Double consumoEstimadoLitros) { this.consumoEstimadoLitros = consumoEstimadoLitros; }

    public Double getCo2EmitidoKg() { return co2EmitidoKg; }
    public void setCo2EmitidoKg(Double co2EmitidoKg) { this.co2EmitidoKg = co2EmitidoKg; }

    public String getIndiceImpactoAmbiental() { return indiceImpactoAmbiental; }
    public void setIndiceImpactoAmbiental(String indiceImpactoAmbiental) { this.indiceImpactoAmbiental = indiceImpactoAmbiental; }
}