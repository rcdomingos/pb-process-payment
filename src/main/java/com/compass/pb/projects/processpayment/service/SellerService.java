package com.compass.pb.projects.processpayment.service;

import com.compass.pb.projects.processpayment.domain.SellerRequest;
import com.compass.pb.projects.processpayment.domain.SellerResponse;
import com.compass.pb.projects.processpayment.entity.SellerEntity;
import com.compass.pb.projects.processpayment.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository repository;

    private final ModelMapper mapper;

    public SellerResponse addNewSeller(SellerRequest request) {
        log.info("addNewSeller() - start saved new seller");
        SellerEntity entity = new SellerEntity();
        entity.setName(request.getName());
        entity.setClientId(request.getClientId());
        entity.setApiKey(UUID.randomUUID());

        SellerEntity saved = repository.save(entity);

        return mapper.map(saved, SellerResponse.class);
    }

    public SellerResponse getSellerById(UUID id) {
        SellerEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.map(entity, SellerResponse.class);
    }
}
