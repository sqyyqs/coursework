package com.sqy.delivery.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class PagingResponse<T> {
    int page;
    long total;
    int limitOnPage;
    Collection<T> data;
    boolean isSuccess;
    Collection<String> errors;

    public static <T> PagingResponse<T>.Builder newPageBuilder() {
        return new PagingResponse().new Builder();
    }


    public class Builder {
        public Builder() {
        }

        public PagingResponse<T> ok(Collection<T> data, long total, int page, int limitOnPage) {
            PagingResponse.this.data = data;
            PagingResponse.this.page = page;
            PagingResponse.this.total = total;
            PagingResponse.this.limitOnPage = limitOnPage;
            PagingResponse.this.isSuccess = true;
            return PagingResponse.this;
        }

        public PagingResponse<T>.Builder addError(String error) {
            if (StringUtils.hasText(error)) {
                if (PagingResponse.this.errors == null) {
                    PagingResponse.this.errors = new ArrayList<>();
                }

                PagingResponse.this.errors.add(error);
                return this;
            } else {
                return this;
            }
        }

        public PagingResponse<T> build() {
            return CollectionUtils.isEmpty(PagingResponse.this.errors) ? this.fail() : PagingResponse.this;
        }

        public PagingResponse<T> fail() {
            PagingResponse.this.isSuccess = false;
            return PagingResponse.this;
        }
    }

}
