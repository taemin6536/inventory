package com.v2.common.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING("예약 대기 상태"),
    CONFIRMED("예약 확정 상태"),
    CANCELLED("예약 취소 상태"),
    EXPIRED("예약 만료 상태");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

}
