package com.sqy.delivery.domain.courier;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.packageEntity.Package;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "courier")
@Table(name = "courier")
public class Courier {
    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "courierStatus", nullable = false)
    @Enumerated(value = EnumType.STRING)
    CourierStatus courierStatus;

    @Embedded
    ClientDetails courierDetails;

    @OneToMany(mappedBy = "courier")
    Set<Package> packageHistory;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Courier{");
        sb.append("id=").append(id);
        sb.append(", courierStatus=").append(courierStatus);
        sb.append(", courierDetails=").append(courierDetails);
        sb.append('}');
        return sb.toString();
    }
}
