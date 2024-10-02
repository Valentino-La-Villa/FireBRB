package com.digitalHouse.FireBrB.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest {
    private Integer limit;
    private Boolean random;
    private String sortBy;
    private String sortDirection;

    public FilterRequest(Integer limit, Boolean random) {
        this.limit = limit;
        this.random = random;
    }

    public FilterRequest(Integer limit) {
        this.limit = limit;
    }
}


