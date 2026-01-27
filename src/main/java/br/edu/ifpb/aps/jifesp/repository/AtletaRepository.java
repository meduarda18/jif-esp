package br.edu.ifpb.aps.jifesp.repository;

import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.entity.Situacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtletaRepository extends JpaRepository<AtletaEntity, Long> {
    List<AtletaEntity> findBySituacao(Situacao situacao);
}
