package br.edu.ifpb.aps.jifesp.repository;


import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeEntity, Long> {
}
