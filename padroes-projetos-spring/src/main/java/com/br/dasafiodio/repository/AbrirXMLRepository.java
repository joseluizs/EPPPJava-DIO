package com.br.dasafiodio.repository;

import com.br.dasafiodio.model.AbrirXML;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrirXMLRepository extends JpaRepository<AbrirXML, Long> {
}
