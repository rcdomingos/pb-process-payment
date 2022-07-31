package com.compass.pb.projects.processpayment.repository;

import com.compass.pb.projects.processpayment.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<SellerEntity, UUID> {

    Optional<SellerEntity> findByClientId(String clientId);
}
