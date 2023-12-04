package com.sqy.delivery.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserSearchRequestDto(
        Set<Long> ids,
        boolean includeSuspended,
        String value
) {
    @JsonCreator
    public UserSearchRequestDto(@JsonProperty("ids") Set<Long> ids,
                                @JsonProperty("includeSuspended") boolean includeSuspended,
                                @JsonProperty("value") String value) {
        this.ids = ids;
        this.includeSuspended = includeSuspended;
        this.value = value;
    }

    public UserSearchRequestDto(Set<Long> ids, String value) {
        this(ids, false, value);
    }
}
