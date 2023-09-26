package com.br.dasafiodio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.List;

@Entity
public class AbrirXML {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudoXML;

    public AbrirXML() { }

    public AbrirXML(String conteudoXML) {
        this.conteudoXML = conteudoXML;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudoXML() {
        return conteudoXML;
    }

    public void setConteudoXML(String conteudoXML) {
        this.conteudoXML = conteudoXML;
    }


}
