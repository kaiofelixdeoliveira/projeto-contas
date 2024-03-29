package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.Transferencia;
import com.br.app.contas.domain.model.TransferenciaModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaJpaRepository extends JpaRepository<Transferencia, Long> {

    @Cacheable("transferencia")
    @Query(value = "SELECT t FROM Transferencia t WHERE t.contaOrigem.numeroConta = :numeroConta AND t.dataHora BETWEEN :dataInicio AND :dataFim")
    List<Transferencia> findAllTransferenciasByContaOrigemAndData(@Param("numeroConta") String numeroConta, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}