package com.compass.pb.projects.processpayment.service;

import com.compass.pb.projects.processpayment.constants.*;
import com.compass.pb.projects.processpayment.domain.request.CardRequest;
import com.compass.pb.projects.processpayment.domain.PaymentAuthorization;
import com.compass.pb.projects.processpayment.domain.request.PaymentRequest;
import com.compass.pb.projects.processpayment.domain.response.PaymentResponse;
import com.compass.pb.projects.processpayment.dto.PaymentDto;
import com.compass.pb.projects.processpayment.entity.PaymentEntity;
import com.compass.pb.projects.processpayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProcessPaymentService {

    private final ModelMapper mapper;

    private final PaymentRepository repository;

    public PaymentResponse processPayment(PaymentRequest payment) {
        PaymentDto paymentDto = analsyRequest(payment);
        PaymentEntity saved = repository.save(mapper.map(paymentDto, PaymentEntity.class));
        return paymentEntityToResponse(saved);
    }

    private PaymentDto analsyRequest(PaymentRequest request) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setSellerId(UUID.fromString(request.getSellerId()));
        paymentDto.setCustomerIdType(CustomerIdType.valueOf(request.getCustomer().getType()));
        paymentDto.setCustomerNumber(request.getCustomer().getNumber());
        paymentDto.setPaymentTypeId(PaymentType.valueOf(request.getPaymentTypeId()));
        paymentDto.setCurrencyType(CurrencyType.valueOf(request.getCurrencyType()));
        paymentDto.setTransactionAmount(request.getTransactionAmount());
        paymentDto.setReceivedAt(LocalDateTime.now());

        PaymentAuthorization authorization = cardValidation(request.getCard(), request.getTransactionAmount());

        paymentDto.setAuthorizationCode(authorization.getAuthorizationCode());
        paymentDto.setReasonCode(authorization.getReasonCode());
        paymentDto.setReasonMessage(authorization.getReasonMessage());
        paymentDto.setAuthorizedAt(authorization.getAuthorizedAt());

        if (authorization.getReasonCode() == 0) {
            paymentDto.setStatus(PaymentStatus.APPROVED);
        } else {
            paymentDto.setStatus(PaymentStatus.REPROVED);
        }
        return paymentDto;
    }


    private PaymentAuthorization cardValidation(CardRequest cardRequest, Double amount) {
        PaymentAuthorization paymentAuthorization = new PaymentAuthorization();
        paymentAuthorization.setAuthorizedAt(LocalDateTime.now().plusSeconds(10));
        paymentAuthorization.setAuthorizationCode(new Date().getTime());

        //check invalid card
        if (cardRequest == null) {
            paymentAuthorization.setReasonCode(ReasonCodes.INVALID_CARD.getCode());
            paymentAuthorization.setReasonMessage(ReasonCodes.INVALID_CARD.getMessage());
            return paymentAuthorization;
        }

        // check expired card
        Year expiradeYear = cardRequest.getExpirationYear().length() == 4
                ? Year.parse(cardRequest.getExpirationYear())
                : Year.parse(cardRequest.getExpirationYear(), DateTimeFormatter.ofPattern("yy"));
        int expiradeMonth = Integer.parseInt(cardRequest.getExpirationMonth());
        LocalDate expiradeDate = LocalDate.of(expiradeYear.getValue(), expiradeMonth, 15);
        if (LocalDate.now().isAfter(expiradeDate)) {
            paymentAuthorization.setReasonCode(ReasonCodes.EXPIRED.getCode());
            paymentAuthorization.setReasonMessage(ReasonCodes.EXPIRED.getMessage());
            return paymentAuthorization;
        }

        //check card limit
        if (amount > 2000D) {
            paymentAuthorization.setReasonCode(ReasonCodes.NO_FUNDS.getCode());
            paymentAuthorization.setReasonMessage(ReasonCodes.NO_FUNDS.getMessage());
            return paymentAuthorization;
        }

        paymentAuthorization.setReasonCode(ReasonCodes.OK.getCode());
        paymentAuthorization.setReasonMessage(ReasonCodes.OK.getMessage());

        return paymentAuthorization;
    }

    private PaymentResponse paymentEntityToResponse(PaymentEntity entity) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(entity.getId());
        response.setSellerId(entity.getSellerId().toString());
        response.setTransactionAmount(entity.getTransactionAmount());
        response.setCurrencyType(entity.getCurrencyType());
        response.setStatus(entity.getStatus());
        response.setReceivedAt(entity.getReceivedAt());

        PaymentAuthorization authorization = new PaymentAuthorization();
        authorization.setAuthorizationCode(entity.getAuthorizationCode());
        authorization.setReasonCode(entity.getReasonCode());
        authorization.setReasonMessage(entity.getReasonMessage());
        authorization.setAuthorizedAt(entity.getAuthorizedAt());

        response.setAuthorization(authorization);

        return response;
    }
}
