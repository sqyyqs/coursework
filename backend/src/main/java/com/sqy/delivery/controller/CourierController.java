package com.sqy.delivery.controller;

import com.sqy.delivery.dto.courier.CourierDto;
import com.sqy.delivery.dto.courier.CourierSearchRequestDto;
import com.sqy.delivery.dto.courier.CourierUpdateStatusRequestDto;
import com.sqy.delivery.dto.courier.CreateCourierRequestDto;
import com.sqy.delivery.service.CourierService;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courier")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Курьер api.")
public class CourierController {

    private final CourierService courierService;

    @PostMapping("/search")
    @CrossOrigin("*")
    @Operation(summary = "Поиск с пагинацией.")
    public ResponseEntity<PagingResponse<CourierDto>> search(@RequestBody PagingRequest<CourierSearchRequestDto> courierSearchRequestDto) {
        log.info("Invoke search({}).", courierSearchRequestDto);
        return ResponseEntity.ok(courierService.search(courierSearchRequestDto));
    }

    @PostMapping("/create")
    @CrossOrigin("*")
    @Operation(summary = "Создание нового курьера(статус будет ON_MODERATING).")
    public ResponseEntity<CourierDto> create(@RequestBody CreateCourierRequestDto courierCreateDto) {
        log.info("Invoke create({}).", courierCreateDto);
        return courierService.create(courierCreateDto);
    }

    @DeleteMapping("/suspend/{id}")
    @CrossOrigin("*")
    @Operation(summary = "Удаление курьера из базы(ему проставляется status=SUSPENDED).")
    public ResponseEntity<CourierDto> suspend(@PathVariable("id") long id) {
        log.info("Invoke suspend({}).", id);
        return courierService.suspendById(id);
    }

    @PutMapping("/updateStatus")
    @CrossOrigin("*")
    @Operation(summary = "Обновление статуса, не работает с newStatus = ON_MODERATING и SUSPENDED, использовать другие эндпоинты для этого.")
    public ResponseEntity<CourierDto> updateStatus(@RequestBody CourierUpdateStatusRequestDto courierUpdateStatusRequestDto) {
        log.info("Invoke updateStatus({}).", courierUpdateStatusRequestDto);
        return courierService.updateStatus(courierUpdateStatusRequestDto);
    }


}
