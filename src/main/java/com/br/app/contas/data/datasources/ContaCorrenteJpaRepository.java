package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.ContaCorrenteEntity;
import com.br.app.contas.domain.model.ContaCorrenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteJpaRepository extends JpaRepository<ContaCorrenteEntity, Long> {

    ContaCorrenteModel findByNumeroConta(String numero);

    boolean findIsActiveByNumeroConta(String numeroConta);


}