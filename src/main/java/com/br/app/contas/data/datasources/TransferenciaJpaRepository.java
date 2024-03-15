package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.Transferencia;
import com.br.app.contas.domain.model.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaJpaRepository extends JpaRepository<Transferencia, Long> {

    @Modifying
    @Query("UPDATE Transferencia t SET t.valor = :newValue WHERE t.contaDestino.numeroConta = :numeroConta")  // Assuming you want to update the "valor" field
    void updateTransferencia(@Param("numeroConta") String numeroConta, @Param("newValue") Double newValue);

    @Query("SELECT t FROM Transferencia t WHERE t.contaOrigem.numeroConta = :numeroConta AND t.dataHora BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaModel> findByContaIdAndDataHoraBetween(@Param("numeroConta") String numeroConta, LocalDateTime dataInicio, LocalDateTime dataFim);


}