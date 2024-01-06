package com.sqy.delivery.service.mapper;

import com.sqy.delivery.domain.Address;
import com.sqy.delivery.domain.packageEntity.Package;
import com.sqy.delivery.domain.packageEntity.PackageStatus;
import com.sqy.delivery.domain.user.User;
import com.sqy.delivery.dto.packagedto.CreatePackageRequestDto;
import com.sqy.delivery.dto.packagedto.PackageDto;

public class PackageMapper {

    public static PackageDto toDto(Package packageEntity) {
        return PackageDto.builder()
                .id(packageEntity.getId())
                .packageStatus(packageEntity.getPackageStatus())
                .packageDetails(packageEntity.getPackageDetails())
                .fromAddress(packageEntity.getFromAddress() == null ? new Address() : packageEntity.getFromAddress())
                .from(UserMapper.toDto(packageEntity.getFrom()))
                .toAddress(packageEntity.getToAddress() == null ? new Address() : packageEntity.getToAddress())
                .to(UserMapper.toDto(packageEntity.getTo()))
                .courier(packageEntity.getCourier() == null ? null : CourierMapper.toDto(packageEntity.getCourier()))
                .build();
    }

    public static Package fromCreateRequestDto(CreatePackageRequestDto createPackageRequestDto) {
        return Package.builder()
                .packageStatus(PackageStatus.UNDER_MODERATOR_CONSIDERATION)
                .packageDetails(createPackageRequestDto.packageDetails())
                .fromAddress(createPackageRequestDto.fromAddress())
                .toAddress(createPackageRequestDto.toAddress())
                .from(User.builder().id(createPackageRequestDto.fromUserId()).build())
                .to(User.builder().id(createPackageRequestDto.toUserId()).build())
                .courier(null)
                .build();
    }
}
