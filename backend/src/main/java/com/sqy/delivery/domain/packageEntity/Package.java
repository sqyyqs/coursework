package com.sqy.delivery.domain.packageEntity;

import com.sqy.delivery.domain.Address;
import com.sqy.delivery.domain.PackageDetails;
import com.sqy.delivery.domain.courier.Courier;
import com.sqy.delivery.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "package")
@Table(name = "package")
public class Package {

    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "packageStatus", nullable = false)
    @Enumerated(value = EnumType.STRING)
    PackageStatus packageStatus;

    @Embedded
    PackageDetails packageDetails;

    @Transient
    Address fromAddress;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    User from;

    @Transient
    Address toAddress;

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    User to;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    Courier courier;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Package{");
        sb.append("id=").append(id);
        sb.append(", packageStatus=").append(packageStatus);
        sb.append(", packageDetails=").append(packageDetails);
        sb.append(", fromAddress=").append(fromAddress);
        sb.append(", from=").append(from);
        sb.append(", toAddress=").append(toAddress);
        sb.append(", to=").append(to);
        sb.append(", courier=").append(courier);
        sb.append('}');
        return sb.toString();
    }
}
