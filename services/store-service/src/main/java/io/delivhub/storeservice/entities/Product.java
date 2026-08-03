package io.delivhub.storeservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;


    // Поля для доставки:
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Embedded
    private Dimensions dimensions;

    // Указывается, если можно посчитать количество товара на складе
    @Column(name = "in_stock")
    private Integer inStock;

    // Указывается, если нельзя посчитать количество товара на складе
    @Column(name = "available")
    private Boolean available;


    // Поля для статистики:
    @CreatedDate
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // Поля для связей:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
