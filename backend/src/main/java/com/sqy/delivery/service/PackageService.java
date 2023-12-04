package com.sqy.delivery.service;

import com.sqy.delivery.dto.packagedto.CreatePackageRequestDto;
import com.sqy.delivery.dto.packagedto.PackageAppointCourierRequestDto;
import com.sqy.delivery.dto.packagedto.PackageDto;
import com.sqy.delivery.dto.packagedto.PackageSearchRequestDto;
import com.sqy.delivery.dto.packagedto.PackageUpdateStatusRequestDto;
import com.sqy.delivery.util.PagingRequest;
import com.sqy.delivery.util.PagingResponse;
import org.springframework.http.ResponseEntity;

public interface PackageService {

    PackageDto create(CreatePackageRequestDto createPackageRequestDto);

    ResponseEntity<PackageDto> appointCourier(PackageAppointCourierRequestDto packageAppointCourierRequestDto);

    ResponseEntity<PackageDto> updateStatus(PackageUpdateStatusRequestDto packageUpdateStatusRequestDto);

    PagingResponse<PackageDto> search(PagingRequest<PackageSearchRequestDto> request);
}
