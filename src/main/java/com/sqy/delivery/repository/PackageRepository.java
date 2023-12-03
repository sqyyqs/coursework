package com.sqy.delivery.repository;

import com.sqy.delivery.domain.packageEntity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long>, JpaSpecificationExecutor<Package> {
}
