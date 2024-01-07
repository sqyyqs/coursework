package com.sqy.delivery.controller;

import com.sqy.delivery.dto.packagedto.CreatePackageRequestDto;
import com.sqy.delivery.dto.packagedto.PackageAppointCourierRequestDto;
import com.sqy.delivery.dto.packagedto.PackageDto;
import com.sqy.delivery.dto.packagedto.PackageSearchRequestDto;
import com.sqy.delivery.dto.packagedto.PackageUpdateStatusRequestDto;
import com.sqy.delivery.service.PackageService;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/package")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Посылки api.")
public class PackageController {

    private final PackageService packageService;

    @PostMapping("/create")
    @CrossOrigin("*")
    @Operation(summary = "Создание посылки(адрес сейчас никуда не сохраняется но нужно проставить хотя бы одно поле).")
    public ResponseEntity<PackageDto> create(@RequestBody CreatePackageRequestDto createPackageRequestDto) {
        log.info("Invoke create({}).", createPackageRequestDto);
        return ResponseEntity.ok(packageService.create(createPackageRequestDto));
    }

    @PostMapping("/search")
    @CrossOrigin("*")
    @Operation(summary = "Поиск с пагинацией.")
    public ResponseEntity<PagingResponse<PackageDto>> search(@RequestBody PagingRequest<PackageSearchRequestDto> createPackageRequestDto) {
        log.info("Invoke search({}).", createPackageRequestDto);
        return ResponseEntity.ok(packageService.search(createPackageRequestDto));
    }

    @PutMapping("/courierAppointment")
    @CrossOrigin("*")
    @Operation(summary = "Назначить курьера на посылку(меняет статус на COURIER_APPOINTED), нужно отдельно поменять статус курьеру.")
    public ResponseEntity<PackageDto> appointCourier(@RequestBody PackageAppointCourierRequestDto packageAppointCourierRequestDto) {
        log.info("Invoke appointCourier({}).", packageAppointCourierRequestDto);
        return packageService.appointCourier(packageAppointCourierRequestDto);
    }

    @PutMapping("/updateStatus")
    @CrossOrigin("*")
    @Operation(summary = "Обновить статус посылки(кроме UNDER_MODERATOR_CONSIDERATION и COURIER_APPOINTED)")
    public ResponseEntity<PackageDto> updateStatus(@RequestBody PackageUpdateStatusRequestDto packageAppointCourierRequestDto) {
        log.info("Invoke updateStatus({}).", packageAppointCourierRequestDto);
        return packageService.updateStatus(packageAppointCourierRequestDto);
    }
}
