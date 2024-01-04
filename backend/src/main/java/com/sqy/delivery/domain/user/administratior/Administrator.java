package com.sqy.delivery.domain.user.administratior;

import com.sqy.delivery.domain.ClientDetails;
import com.sqy.delivery.domain.user.AbstractUser;
import com.sqy.delivery.domain.user.UserCredentials;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity(name = "administrator")
@Table(name = "administrator")
public class Administrator extends AbstractUser {

    @Builder
    public Administrator(Long id, ClientDetails userDetails, UserCredentials credentials) {
        super(id, userDetails, credentials);
    }

    public Administrator() {
    }
}
