package cadsok.restaurant.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "addresses")
public class StreetAddressEntity extends BaseEntity {
    @Column(updatable = false)
    private String street;
    @Column(updatable = false)
    private String city;
    private String postalCode;
}
