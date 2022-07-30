package com.compass.pb.projects.processpayment.repository;


import com.compass.pb.projects.processpayment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
