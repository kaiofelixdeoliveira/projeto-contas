package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.domain.model.ContaModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaJpaRepository extends JpaRepository<Conta, Long> {
    @Cacheable("conta")
    Conta findByNumeroConta(String numero);

    @Cacheable("conta")
    Conta findByNumeroContaAndStatus(String numeroConta, String status);



}