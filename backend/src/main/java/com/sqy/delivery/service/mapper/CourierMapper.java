package com.sqy.delivery.service.mapper;

import com.sqy.delivery.domain.courier.Courier;
import com.sqy.delivery.domain.courier.CourierStatus;
import com.sqy.delivery.dto.courier.CourierDto;
import com.sqy.delivery.dto.courier.CreateCourierRequestDto;

public class CourierMapper {

    public static CourierDto toDto(Courier courier) {
        return CourierDto.builder()
                .id(courier.getId())
                .courierStatus(courier.getCourierStatus())
                .courierDetails(courier.getCourierDetails())
                .build();
    }

    public static Courier fromCreateDto(CreateCourierRequestDto createCourierRequestDto) {
        return Courier.builder()
                .courierDetails(createCourierRequestDto.courierDetails())
                .courierStatus(CourierStatus.ON_MODERATING)
                .build();
    }
}
