package cadsok.order.data.source.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StreetAddressEntity extends BaseEntityUUID {
    @Column(updatable = false)
    private String street;
    @Column(updatable = false)
    private String city;
    private String postalCode;
}
