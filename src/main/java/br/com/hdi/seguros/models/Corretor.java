package br.com.hdi.seguros.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class Corretor implements Serializable {

    private String nome;
    private String documento;
    private String codigo;
    private LocalDateTime dataCriacao;
    private double taxaComissao;
    private boolean ativo;
}