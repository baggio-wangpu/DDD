package com.bee.master.application.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {
    private PageMate meta;

    private List<T> data;

    private static PageMate generateMeta(Page page) {
        return PageMate.builder()
                .pageIndex(page.getNumber())
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalSize(page.getTotalElements())
                .build();
    }

    public static <T, R> PageVO<T> from(Page<R> page, Function<? super R, ? extends T> mapper) {
        return PageVO.<T>builder()
                .meta(generateMeta(page))
                .data(page.getContent().stream().map(mapper).collect(toList()))
                .build();
    }

    @Data
    @Builder
    static class PageMate {
        private long pageIndex;
        private long pageSize;
        private long totalPage;
        private long totalSize;
    }
}
