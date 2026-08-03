package io.delivhub.deliveryservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED(Group.PROCESSING),
    PENDING_PAYMENT(Group.PROCESSING),
    CONFIRMED(Group.PROCESSING),

    IN_PROGRESS(Group.MERCHANT_SIDE),
    READY_FOR_PICKUP(Group.MERCHANT_SIDE),

    SEARCHING_COURIER(Group.DELIVERY),
    COURIER_ASSIGNED(Group.DELIVERY),
    IN_TRANSIT(Group.DELIVERY),
    ARRIVED(Group.DELIVERY),
    RETURNING(Group.DELIVERY),

    DELIVERED(Group.FINAL),
    CANCELLED(Group.FINAL), // Отмена до начала исполнения (без финансовых потерь)
    FAILED(Group.FINAL), // Отмена после начала исполнения (с финансовыми потерями)
    RETURNED(Group.FINAL);

    private final Group group;

    OrderStatus(Group group) {
        this.group = group;
    }

    public enum Group {
        PROCESSING,
        MERCHANT_SIDE,
        DELIVERY,
        FINAL
    }
}
