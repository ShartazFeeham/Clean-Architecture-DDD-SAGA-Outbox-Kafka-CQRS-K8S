package cadsok.restaurant.domain.application.ports.input.event;

public interface OrderPaidEventListener {

    void handleOrderPaidEvent(String orderId, String detailJson);

}
