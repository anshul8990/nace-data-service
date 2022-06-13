package com.dbclm.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbclm.persistence.entity.NaceEntity;

import java.util.Optional;

@Repository
public interface NaceRepository extends JpaRepository<NaceEntity, Long> {
    Optional<NaceEntity> findNaceByOrder(String order);
}
