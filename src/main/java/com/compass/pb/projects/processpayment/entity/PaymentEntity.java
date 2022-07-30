package com.compass.pb.projects.processpayment.entity;

import com.compass.pb.projects.processpayment.constants.CurrencyType;
import com.compass.pb.projects.processpayment.constants.CustomerIdType;
import com.compass.pb.projects.processpayment.constants.PaymentStatus;
import com.compass.pb.projects.processpayment.constants.PaymentType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "customer_document_number")
    private String customerNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_document_type")
    private CustomerIdType customerIdType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyType currencyType;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "authorization_code")
    private Long authorizationCode;

    @Column(name = "reason_code")
    private int reasonCode;

    @Column(name = "reason_message")
    private String reasonMessage;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "authorized_at")
    private LocalDateTime authorizedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentEntity that = (PaymentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
