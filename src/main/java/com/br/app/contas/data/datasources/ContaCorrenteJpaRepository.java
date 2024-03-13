package com.br.app.contas.data.datasources;

import com.br.app.contas.data.entities.ContaCorrente;
import com.br.app.contas.domain.model.ContaCorrenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteJpaRepository extends JpaRepository<ContaCorrente, Long> {

    ContaCorrenteModel findByNumeroConta(String numero);

    boolean findIsActiveByNumeroConta(String numeroConta);


}