package com.compass.pb.projects.processpayment.repository;

import com.compass.pb.projects.processpayment.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<SellerEntity, UUID> {
}
