package com.sqy.delivery.repository;

import com.sqy.delivery.domain.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long>, JpaSpecificationExecutor<Courier> {

}
