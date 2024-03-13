package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.domain.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaJpaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE c.numeroConta = :numero")
    ContaModel findByNumeroConta(String numero);

    @Query("SELECT c.ativa FROM Conta c WHERE c.numeroConta = :numeroConta")
    boolean findIsActiveByNumeroConta(String numeroConta);



}