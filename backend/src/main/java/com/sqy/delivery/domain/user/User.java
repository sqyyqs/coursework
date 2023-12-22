package com.sqy.delivery.domain.user;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.packageEntity.Package;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "user")
@Table(name = "users")
public class User extends AbstractUser {

    @OneToMany(mappedBy = "from")
    Set<Package> packageHistoryFrom;

    @OneToMany(mappedBy = "to")
    Set<Package> packageHistoryTo;

    @Column(name = "isSuspended", nullable = false)
    private boolean isSuspended;

    @Builder
    public User(Long id, ClientDetails userDetails, boolean isSuspended, UserCredentials credentials, Set<Package> packageHistoryFrom, Set<Package> packageHistoryTo) {
        super(id, userDetails, credentials);
        this.isSuspended = isSuspended;
        this.packageHistoryFrom = packageHistoryFrom;
        this.packageHistoryTo = packageHistoryTo;
    }
}

