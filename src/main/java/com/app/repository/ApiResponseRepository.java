package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.ApiResponseStore;

@Repository
public interface ApiResponseRepository extends JpaRepository<ApiResponseStore, Long> {

}
