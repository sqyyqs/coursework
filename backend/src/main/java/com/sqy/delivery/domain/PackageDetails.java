package com.sqy.delivery.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PackageDetails {
    double height;
    double width;
    double length;
    double weight;
    @Nullable
    String description;
}
