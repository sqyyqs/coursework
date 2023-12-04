package com.sqy.delivery.service.impl;

import com.sqy.delivery.domain.courier.Courier;
import com.sqy.delivery.domain.packageEntity.Package;
import com.sqy.delivery.domain.packageEntity.PackageStatus;
import com.sqy.delivery.dto.packagedto.CreatePackageRequestDto;
import com.sqy.delivery.dto.packagedto.PackageAppointCourierRequestDto;
import com.sqy.delivery.dto.packagedto.PackageDto;
import com.sqy.delivery.dto.packagedto.PackageSearchRequestDto;
import com.sqy.delivery.dto.packagedto.PackageUpdateStatusRequestDto;
import com.sqy.delivery.repository.CourierRepository;
import com.sqy.delivery.repository.PackageRepository;
import com.sqy.delivery.service.PackageService;
import com.sqy.delivery.service.mapper.PackageMapper;
import com.sqy.delivery.service.specification.PackageSpecificationBuilder;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sqy.delivery.domain.packageEntity.PackageStatus.COURIER_APPOINTED;
import static com.sqy.delivery.domain.packageEntity.PackageStatus.UNDER_MODERATOR_CONSIDERATION;
import static com.sqy.delivery.domain.packageEntity.PackageStatus.WAITING_FOR_COURIER;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageServiceDatabase implements PackageService {

    private final PackageRepository packageRepository;
    private final CourierRepository courierRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public PagingResponse<PackageDto> search(PagingRequest<PackageSearchRequestDto> request) {
        log.info("Invoke search({}).", request);
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getLimitOnPage());
        Page<Package> packagesPage = packageRepository.findAll(
                PackageSpecificationBuilder.build(request.getData()),
                pageRequest
        );

        List<PackageDto> mappedResult = packagesPage.stream()
                .map(PackageMapper::toDto)
                .toList();

        return PagingResponse.<PackageDto>newPageBuilder()
                .ok(mappedResult,
                        packagesPage.getTotalElements(),
                        request.getPage(),
                        request.getLimitOnPage());
    }

    @Override
    @Transactional
    public PackageDto create(CreatePackageRequestDto createPackageRequestDto) {
        log.info("Invoke create({}).", createPackageRequestDto);
        Package savedPackage = packageRepository.saveAndFlush(PackageMapper.fromCreateRequestDto(createPackageRequestDto));
        entityManager.refresh(savedPackage);
        return PackageMapper.toDto(savedPackage);
    }

    @Override
    @Transactional
    public ResponseEntity<PackageDto> appointCourier(PackageAppointCourierRequestDto packageAppointCourierRequestDto) {
        log.info("Invoke appointCourier({}).", packageAppointCourierRequestDto);
        Package packageEntity = packageRepository.findById(packageAppointCourierRequestDto.packageId()).orElse(null);
        Courier courier = courierRepository.findById(packageAppointCourierRequestDto.courierId()).orElse(null);
        if (packageEntity == null || courier == null) {
            return ResponseEntity.notFound().build();
        }

        if (packageEntity.getCourier() != null || packageEntity.getPackageStatus() != WAITING_FOR_COURIER) {
            return ResponseEntity.badRequest().build();
        }
        packageEntity.setCourier(Courier.builder().id(packageAppointCourierRequestDto.courierId()).build());
        packageEntity.setPackageStatus(COURIER_APPOINTED);
        Package saved = packageRepository.save(packageEntity);
        entityManager.refresh(saved);
        return ResponseEntity.ok(PackageMapper.toDto(saved));
    }

    @Override
    @Transactional
    public ResponseEntity<PackageDto> updateStatus(PackageUpdateStatusRequestDto packageUpdateStatusRequestDto) {
        log.info("Invoke updateStatus({}).", packageUpdateStatusRequestDto);
        return processUpdateStatus(packageUpdateStatusRequestDto.packageId(), packageUpdateStatusRequestDto.newStatus());
    }

    @Transactional
    public ResponseEntity<PackageDto> processUpdateStatus(long packageId, PackageStatus newStatus) {
        Package packageEntity = packageRepository.findById(packageId).orElse(null);
        if (packageEntity == null) {
            return ResponseEntity.notFound().build();
        }

        if (newStatus == UNDER_MODERATOR_CONSIDERATION || newStatus.getOrder() - packageEntity.getPackageStatus().getOrder() != 1) {
            return ResponseEntity.badRequest().build();
        }

        packageEntity.setPackageStatus(newStatus);
        Package saved = packageRepository.save(packageEntity);
        return ResponseEntity.ok(PackageMapper.toDto(saved));
    }

    /**
     * Возможные переходы состояния
     * UNDER_MODERATOR_CONSIDERATION -> ACCEPTED_BY_MODERATOR, CANCELED_BY_MODERATOR;
     * ACCEPTED_BY_MODERATOR -> WAITING_FOR_COURIER;
     * WAITING_FOR_COURIER -> COURIER_APPOINTED;
     * COURIER_APPOINTED  -> COURIER_ON_THE_WAY_TO_RECEIVE;
     * COURIER_ON_THE_WAY_TO_RECEIVE  -> COURIER_ACCEPT_PACKAGE, COURIER_DENY_PACKAGE;
     * COURIER_ACCEPT_PACKAGE  -> COURIER_ON_THE_WAY_TO_DELIVERY;
     * COURIER_ON_THE_WAY_TO_DELIVERY   -> PACKAGE_DELIVERED;
     * CANCELED_BY_MODERATOR, COURIER_DENY_PACKAGE -> не обновить;
     */
}
