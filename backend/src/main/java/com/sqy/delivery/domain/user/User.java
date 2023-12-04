package com.sqy.delivery.domain.user;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.packageEntity.Package;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "user")
@Table(name = "users")
public class User {
    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    ClientDetails userDetails;

    @Column(name = "isSuspended", nullable = false)
    boolean isSuspended;

    @OneToMany(mappedBy = "from")
    Set<Package> packageHistoryFrom;

    @OneToMany(mappedBy = "to")
    Set<Package> packageHistoryTo;


}
