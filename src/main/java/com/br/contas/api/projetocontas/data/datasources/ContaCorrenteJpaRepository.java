package com.br.contas.api.projetocontas.data.datasources;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteJpaRepository extends JpaRepository<ContaCorrente, Long> {

    ContaCorrente findByNumero(String numero);


}
