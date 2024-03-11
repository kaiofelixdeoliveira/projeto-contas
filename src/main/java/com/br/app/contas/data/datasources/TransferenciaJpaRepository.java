package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.TransferenciaEntity;
import com.br.app.contas.domain.model.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaJpaRepository extends JpaRepository<TransferenciaEntity, Long> {

    void atualizarValor(TransferenciaModel transferencia);

    List<TransferenciaModel> findByContaCorrenteIdAndDataHoraBetween(String numeroConta, LocalDateTime dataInicio, LocalDateTime dataFim);


}