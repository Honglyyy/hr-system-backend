package com.ly.hrsystembackend.specification;

import com.ly.hrsystembackend.dto.employeeprofiledto.EmployeeProfileResponse;
import com.ly.hrsystembackend.enums.EmployeeStatus;
import com.ly.hrsystembackend.model.EmployeeProfile;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeProfileSpecification {

    public static Specification<EmployeeProfile> hasStatus(EmployeeStatus status){
        if(status == null){
            return Specification.unrestricted();
        }

        return ((root,
                 query,
                 criteriaBuilder) ->criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<EmployeeProfile> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Specification.unrestricted();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("employeeId")), pattern),
                cb.like(cb.lower(root.get("firstName")), pattern),
                cb.like(cb.lower(root.get("lastName")), pattern),
                cb.like(cb.lower(root.get("email")), pattern)
        );
    }
}
