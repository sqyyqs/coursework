package com.sqy.delivery.service;

import com.sqy.delivery.dto.courier.CourierDto;
import com.sqy.delivery.dto.courier.CourierSearchRequestDto;
import com.sqy.delivery.dto.courier.CourierUpdateStatusRequestDto;
import com.sqy.delivery.dto.courier.CreateCourierRequestDto;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import org.springframework.http.ResponseEntity;

public interface CourierService {

    PagingResponse<CourierDto> search(PagingRequest<CourierSearchRequestDto> request);

    ResponseEntity<CourierDto> create(CreateCourierRequestDto courierCreateDto);

    ResponseEntity<CourierDto> suspendById(long id);

    ResponseEntity<CourierDto> updateStatus(CourierUpdateStatusRequestDto courierUpdateStatusRequestDto);

}
