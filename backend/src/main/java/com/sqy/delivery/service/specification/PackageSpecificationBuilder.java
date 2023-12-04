package com.sqy.delivery.service.specification;

import com.sqy.delivery.domain.packageEntity.Package;
import com.sqy.delivery.dto.packagedto.PackageSearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PackageSpecificationBuilder {

    public static Specification<Package> build(PackageSearchRequestDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = getPredicates(filter, root, cb, query);
            return query.where(cb.and(predicates.toArray(new Predicate[0])))
                    .distinct(true)
                    .orderBy(cb.asc(root.get("id")))
                    .getRestriction();
        };
    }

    private static List<Predicate> getPredicates(PackageSearchRequestDto filter, Root<Package> root, CriteriaBuilder cb, CriteriaQuery<?> query) {
        List<Predicate> predicates = new ArrayList<>();

        if (!CollectionUtils.isEmpty(filter.ids())) {
            predicates.add(root.get("id").in(filter.ids()));
        }

        if (!CollectionUtils.isEmpty(filter.permissibleStatuses())) {
            predicates.add(root.get("packageStatus").in(filter.permissibleStatuses()));
        }

        if (!CollectionUtils.isEmpty(filter.courierIds())) {
            predicates.add(root.join("courier", JoinType.INNER).get("id").in(filter.courierIds()));
        }

        if (!CollectionUtils.isEmpty(filter.userIds())) {
            predicates.add(cb.or(
                    root.join("from", JoinType.INNER).get("id").in(filter.userIds()),
                    root.join("to", JoinType.INNER).get("id").in(filter.userIds())
            ));
        }
        return predicates;
    }

}
