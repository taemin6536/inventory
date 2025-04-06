package com.v2.reservation.domain.model;

import com.v2.common.enums.ReservationStatus;
import com.v2.product.domain.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "reservations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여러 예약이 하나의 제품에 속함 (지연 로딩 적용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer reservedQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;


    public Reservation(Product product, Integer reservedQuantity, LocalDateTime reservedAt, LocalDateTime expiresAt) {
        if (product == null || reservedQuantity == null || reservedAt == null || expiresAt == null) {
            throw new IllegalArgumentException("예약 관련 정보는 null일 수 없습니다.");
        }
        this.product = product;
        this.reservedQuantity = reservedQuantity;
        this.reservedAt = reservedAt;
        this.expiresAt = expiresAt;
        this.status = ReservationStatus.PENDING;
    }



    // 비즈니스 메서드: 예약 확정
    public void confirm() {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("대기 상태인 예약만 확정할 수 있습니다.");
        }
        this.status = ReservationStatus.CONFIRMED;
    }

    // 비즈니스 메서드: 예약 취소
    public void cancel() {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("대기 상태인 예약만 취소할 수 있습니다.");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    // 비즈니스 메서드: 예약 만료 처리
    public void expire() {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("대기 상태인 예약만 만료 처리할 수 있습니다.");
        }
        this.status = ReservationStatus.EXPIRED;
    }
}
