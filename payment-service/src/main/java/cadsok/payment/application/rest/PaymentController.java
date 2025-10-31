package cadsok.payment.application.rest;

import cadsok.payment.domain.application.models.PaymentCreateRequestDto;
import cadsok.payment.domain.application.models.PaymentTrackingResponseDto;
import cadsok.payment.domain.application.ports.input.client.PaymentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    @PostMapping
    public ResponseEntity<PaymentTrackingResponseDto> createPayment(@RequestBody PaymentCreateRequestDto paymentCreateRequestDto) {
        PaymentTrackingResponseDto paymentInitializedResponse = paymentApplicationService.initializePayment(paymentCreateRequestDto);
        return ResponseEntity.ok(paymentInitializedResponse);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentTrackingResponseDto> getPaymentByPaymentId(@PathVariable String paymentId) {
        PaymentTrackingResponseDto trackPaymentResponse = paymentApplicationService.track(paymentId);
       return  ResponseEntity.ok(trackPaymentResponse);
    }
}
