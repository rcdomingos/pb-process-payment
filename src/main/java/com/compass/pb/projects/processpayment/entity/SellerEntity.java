package com.compass.pb.projects.processpayment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "sellers")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seller_id", updatable = false, unique = true, nullable = false)
    private UUID sellerId;

    @Column(name = "name")
    private String name;

    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;

    @Column(name = "api_key", unique = true, nullable = false)
    private UUID apiKey;
}
