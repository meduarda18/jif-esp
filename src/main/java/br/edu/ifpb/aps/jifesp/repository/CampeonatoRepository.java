package br.edu.ifpb.aps.jifesp.repository;

import br.edu.ifpb.aps.jifesp.entity.CampeonatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<CampeonatoEntity, Long>{

}
    