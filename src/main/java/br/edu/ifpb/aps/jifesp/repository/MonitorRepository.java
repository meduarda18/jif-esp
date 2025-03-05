package br.edu.ifpb.aps.jifesp.repository;

import br.edu.ifpb.aps.jifesp.entity.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<MonitorEntity, Long>{
    
}
