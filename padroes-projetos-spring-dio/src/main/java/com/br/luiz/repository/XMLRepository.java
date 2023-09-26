package com.br.luiz.repository;

import com.br.luiz.model.XML;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XMLRepository extends CrudRepository<XML, Long> {
}

