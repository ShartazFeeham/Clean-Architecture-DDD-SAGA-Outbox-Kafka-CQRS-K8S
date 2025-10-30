package cadsok.order.data.mapper;

import commonmodule.domain.exceptions.DomainException;
import commonmodule.domain.values.OrderStatus;

import java.util.Map;

public class OrderStatusMapper {

    private static final Map<Integer, OrderStatus> dbToDomainMap = Map.of(
            0, OrderStatus.PENDING,
            1, OrderStatus.PAID,
            2, OrderStatus.APPROVED,
            3, OrderStatus.CANCELLING,
            4, OrderStatus.CANCELLED
    );

    public static int toDbStatus(OrderStatus orderStatus) {
        return dbToDomainMap.entrySet().stream().filter(entry -> entry.getValue() == orderStatus)
                .findFirst()
                .orElseThrow(() -> new DomainException("Unknown OrderStatus: " + orderStatus))
                .getKey();
    }

    public static OrderStatus toDomainStatus(int dbStatus) {
        OrderStatus orderStatus = dbToDomainMap.get(dbStatus);
        if (orderStatus == null) {
            throw new DomainException("Unknown dbStatus: " + dbStatus);
        }
        return orderStatus;
    }
}
