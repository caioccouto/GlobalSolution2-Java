package org.acme.bo;

import java.time.LocalDate;

public class ValidacaoBO {

    public static void validarNome(String nome) {
        if (nome == null || nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome não pode ser nulo e deve ter ao menos 2 caracteres!");
        }
    }

    public static void validarCnpj(String cnpj) {
        if (cnpj == null) throw new IllegalArgumentException("CNPJ não pode ser nulo!");
        String novoCnpj = cnpj.replaceAll("[^0-9]", "");
        if (novoCnpj.length() != 14) throw new IllegalArgumentException("CNPJ deve conter 14 dígitos!");
    }

    public static void validarCpf(String cpf) {
        if (cpf == null) throw new IllegalArgumentException("CPF não pode ser nulo!");
        String novoCpf = cpf.replaceAll("[^0-9]", "");
        if (novoCpf.length() != 11) throw new IllegalArgumentException("CPF deve conter 11 dígitos!");
    }

    public static void validarPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser nula!");
        }
    }

    public static void validarAnoFabricacao(Integer ano) {
        if (ano == null) throw new IllegalArgumentException("Ano de fabricação não pode ser nulo!");
        int anoAtual = LocalDate.now().getYear();
        if (ano < 1950 || ano > anoAtual) {
            throw new IllegalArgumentException("Ano de fabricação inválido! Deve estar entre 1950 e " + anoAtual + ".");
        }
    }

    public static void validarCapacidadeCarga(Double capacidade) {
        if (capacidade == null || capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade de carga deve ser maior que zero!");
        }
    }

    public static void validarValidadeCnh(LocalDate validade) {
        if (validade == null) throw new IllegalArgumentException("Validade da CNH não pode ser nula!");
        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("CNH vencida! A validade não pode ser uma data passada.");
        }
    }

    public static void validarFatorEmissao(Double fator) {
        if (fator == null || fator <= 0) {
            throw new IllegalArgumentException("Fator de emissão de carbono deve ser maior que zero!");
        }
    }

    public static void validarDistancia(Double distancia) {
        if (distancia == null || distancia <= 0) {
            throw new IllegalArgumentException("Distância deve ser maior que zero!");
        }
    }

    public static void validarCarga(Double carga) {
        if (carga == null || carga <= 0) {
            throw new IllegalArgumentException("Carga transportada deve ser maior que zero!");
        }
    }

    public static void validarDataViagem(LocalDate data) {
        if (data == null) throw new IllegalArgumentException("Data da viagem não pode ser nula!");
        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data da viagem não pode ser uma data futura!");
        }
    }
}
