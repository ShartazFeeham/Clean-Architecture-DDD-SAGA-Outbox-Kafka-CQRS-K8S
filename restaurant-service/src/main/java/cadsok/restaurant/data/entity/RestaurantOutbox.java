package cadsok.restaurant.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantOutbox{

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // This will hold the JSON payload of the event
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb", nullable = false)
    private String payload;

    // Used by Debezium to determine the Kafka Topic Suffix (e.g., 'restaurant' -> 'outbox.event.restaurant')
    @Column(name = "aggregatetype", nullable = false)
    private String aggregateType;

    // Used by Debezium as the Kafka Key to ensure ordering for a specific aggregate instance
    @Column(name = "aggregateid", nullable = false)
    private String aggregateId;

    // Used by Debezium as a message header (e.g., 'CREATE', 'UPDATE')
    @Column(name = "type", nullable = false)
    private String type;

    // Optional: Timestamp for auditing and ordering, though Debezium provides its own timestamp
    @Column(name = "created_at")
    private Instant createdAt;

}
