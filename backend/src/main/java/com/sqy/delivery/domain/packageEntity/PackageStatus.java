package com.sqy.delivery.domain.packageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PackageStatus {

    UNDER_MODERATOR_CONSIDERATION("На рассмотрении модератором", 0),
    ACCEPTED_BY_MODERATOR("Принято модератором", 1),
    CANCELED_BY_MODERATOR("Отклонено модератором", -1),
    WAITING_FOR_COURIER("Ожидается курьер", 2),
    COURIER_APPOINTED("Курьер назначен", 3),
    COURIER_ON_THE_WAY_TO_RECEIVE("Курьер на пути к получению посылки", 4),
    COURIER_DENY_PACKAGE("Курьер отказался от посылки", -1),
    COURIER_ACCEPT_PACKAGE("Курьер принял посылку", 5),
    COURIER_ON_THE_WAY_TO_DELIVERY("Курьер на пути к доставке", 6),
    PACKAGE_DELIVERED("Посылка доставлена", 7);

    private final String value;
    private final int order;


}
