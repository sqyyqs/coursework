package com.sqy.delivery.service.impl;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.courier.Courier;
import com.sqy.delivery.domain.courier.CourierStatus;
import com.sqy.delivery.dto.courier.CourierDto;
import com.sqy.delivery.dto.courier.CourierSearchRequestDto;
import com.sqy.delivery.dto.courier.CourierUpdateStatusRequestDto;
import com.sqy.delivery.dto.courier.CreateCourierRequestDto;
import com.sqy.delivery.repository.CourierRepository;
import com.sqy.delivery.service.CourierService;
import com.sqy.delivery.service.mapper.CourierMapper;
import com.sqy.delivery.service.specification.CourierSpecificationBuilder;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sqy.delivery.domain.courier.CourierStatus.ON_MODERATING;
import static com.sqy.delivery.domain.courier.CourierStatus.SUSPENDED;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierServiceDatabase implements CourierService {

    private final CourierRepository courierRepository;

    @Override
    @Transactional(readOnly = true)
    public PagingResponse<CourierDto> search(PagingRequest<CourierSearchRequestDto> request) {
        log.info("Invoke search({}).", request);
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getLimitOnPage());
        Page<Courier> packagesPage = courierRepository.findAll(
                CourierSpecificationBuilder.build(request.getData()),
                pageRequest
        );

        List<CourierDto> mappedResult = packagesPage.stream()
                .map(CourierMapper::toDto)
                .toList();

        return PagingResponse.<CourierDto>newPageBuilder()
                .ok(mappedResult,
                        packagesPage.getTotalElements(),
                        request.getPage(),
                        request.getLimitOnPage());
    }

    @Override
    @Transactional
    public ResponseEntity<CourierDto> create(CreateCourierRequestDto courierCreateDto) {
        log.info("Invoke create({}).", courierCreateDto);
        ClientDetails courierDetails = courierCreateDto.courierDetails();
        if (!StringUtils.hasText(courierDetails.getPhoneNumber())
                || !StringUtils.hasText(courierDetails.getFirstName())
                || !StringUtils.hasText(courierDetails.getLastName())) {
            return ResponseEntity.badRequest().build();
        }
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(courierCreateDto.courierDetails().getPhoneNumber());
        if (!matcher.matches()) {
            return ResponseEntity.badRequest().build();
        }
        Courier saved = courierRepository.save(CourierMapper.fromCreateDto(courierCreateDto));
        return ResponseEntity.ok(CourierMapper.toDto(saved));
    }

    @Override
    @Transactional
    public ResponseEntity<CourierDto> suspendById(long id) {
        log.info("Invoke suspendById({}).", id);
        return courierRepository.findById(id)
                .map(courier -> {
                    courier.setCourierStatus(SUSPENDED);
                    courierRepository.save(courier);
                    return ResponseEntity.ok(CourierMapper.toDto(courier));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<CourierDto> updateStatus(CourierUpdateStatusRequestDto courierUpdateStatusRequestDto) {
        log.info("Invoke updateStatus({}).", courierUpdateStatusRequestDto);
        return processUpdateStatus(courierUpdateStatusRequestDto.courierId(), courierUpdateStatusRequestDto.newStatus());
    }

    @Transactional
    public ResponseEntity<CourierDto> processUpdateStatus(long courierId, CourierStatus newStatus) {
        Courier courier = courierRepository.findById(courierId).orElse(null);
        if (courier == null) {
            return ResponseEntity.notFound().build();
        }

        if (newStatus == ON_MODERATING || newStatus == SUSPENDED) {
            return ResponseEntity.badRequest().build();
        }

        courier.setCourierStatus(newStatus);
        Courier saved = courierRepository.save(courier);
        return ResponseEntity.ok(CourierMapper.toDto(saved));
    }

}
