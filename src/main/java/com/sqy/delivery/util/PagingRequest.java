package com.sqy.delivery.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class PagingRequest<T> {
    T data;
    int page;
    int limitOnPage;
}
