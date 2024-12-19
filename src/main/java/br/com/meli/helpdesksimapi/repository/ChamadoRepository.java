package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByUsuarioAndMaquininhaAndStatusNot(Usuario usuario, Maquininha maquininha, Status status);

    List<Chamado> findByMaquininhaSerialNumberAndStatusNot(String serialNumber, Status status);

    Page<Chamado> findByUsuarioCustomerId(String customerId, Pageable pageable);

    int countByBalcaoAndStatusNot(Balcao balcao, Status status);

    List<Chamado> findByStatus(Status status);

    List<Chamado> findByStatusOrderByDataChamado(Status status);

}
