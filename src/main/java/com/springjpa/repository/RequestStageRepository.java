package com.springjpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springjpa.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

	@Query(value = "select * from request_stage where owner_id = ?1", nativeQuery=true)
	public List<RequestStage> findByOwnerId(Long owner_id);

	public Page<RequestStage> findAllByRequestId(Long request_id, Pageable pageable);
}
