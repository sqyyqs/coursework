package com.sqy.delivery.service.specification;

import com.sqy.delivery.domain.courier.Courier;
import com.sqy.delivery.dto.courier.CourierSearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CourierSpecificationBuilder {

    public static Specification<Courier> build(CourierSearchRequestDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = getPredicates(filter, root, cb, query);
            return query.where(cb.and(predicates.toArray(new Predicate[0])))
                    .distinct(true)
                    .orderBy(cb.asc(root.get("id")))
                    .getRestriction();
        };
    }

    private static List<Predicate> getPredicates(CourierSearchRequestDto filter, Root<Courier> root, CriteriaBuilder cb, CriteriaQuery<?> query) {
        List<Predicate> predicates = new ArrayList<>();

        if (!CollectionUtils.isEmpty(filter.ids())) {
            predicates.add(root.get("id").in(filter.ids()));
        }

        if (!CollectionUtils.isEmpty(filter.permissibleStatuses())) {
            predicates.add(root.get("courierStatus").in(filter.permissibleStatuses()));
        }

        if (StringUtils.hasText(filter.value())) {
            String preparedValue = '%' + filter.value().toLowerCase() + '%';
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("courierDetails").get("firstName")), preparedValue),
                    cb.like(cb.lower(root.get("courierDetails").get("lastName")), preparedValue))
            );
        }

        return predicates;
    }
}
