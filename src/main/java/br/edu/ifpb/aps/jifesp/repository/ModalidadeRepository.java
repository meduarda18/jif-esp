package br.edu.ifpb.aps.jifesp.repository;

import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository extends JpaRepository<ModalidadeEntity, Long>{

}
