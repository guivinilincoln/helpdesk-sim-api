package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.Chamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> buscarPorUsuarioCustomerId(String customerId, Pageable pageable);

}
