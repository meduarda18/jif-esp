package br.edu.ifpb.aps.jifesp.repository;

import br.edu.ifpb.aps.jifesp.entity.JogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<JogoEntity, Long> {
}