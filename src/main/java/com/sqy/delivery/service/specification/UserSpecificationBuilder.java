package com.sqy.delivery.service.specification;

import com.sqy.delivery.domain.user.User;
import com.sqy.delivery.dto.user.UserSearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserSpecificationBuilder {

    public static Specification<User> build(UserSearchRequestDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = getPredicates(filter, root, cb, query);
            return query.where(cb.and(predicates.toArray(new Predicate[0])))
                    .distinct(true)
                    .orderBy(cb.asc(root.get("id")))
                    .getRestriction();
        };
    }

    private static List<Predicate> getPredicates(UserSearchRequestDto filter, Root<User> root, CriteriaBuilder cb, CriteriaQuery<?> query) {
        List<Predicate> predicates = new ArrayList<>();

        if (!CollectionUtils.isEmpty(filter.ids())) {
            predicates.add(root.get("id").in(filter.ids()));
        }

        if (!filter.includeSuspended()) {
            predicates.add(cb.equal(root.get("isSuspended"), false));
        }

        if (StringUtils.hasText(filter.value())) {
            String preparedValue = '%' + filter.value().toLowerCase() + '%';
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("userDetails").get("firstName")), preparedValue),
                    cb.like(cb.lower(root.get("userDetails").get("lastName")), preparedValue))
            );
        }

        return predicates;
    }
}
