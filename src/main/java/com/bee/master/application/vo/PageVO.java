package com.bee.master.application.vo;

import com.bee.master.application.mapper.GenericMapper;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    public static final String META_KEY_PAGE_INDEX = "pageIndex";
    public static final String META_KEY_PAGE_SIZE = "pageSize";
    public static final String META_KEY_TOTAL_PAGE = "totalPage";
    public static final String META_KEY_TOTAL_SIZE = "totalSize";

    private Map<String, Object> meta;

    private List<T> data;

    private static <T> Map<String, Object> generateMeta(Page<T> page) {
        return ImmutableMap.of(META_KEY_PAGE_INDEX, page.getNumber(),
                META_KEY_PAGE_SIZE, page.getSize(),
                META_KEY_TOTAL_PAGE, page.getTotalPages(),
                META_KEY_TOTAL_SIZE, page.getTotalElements());
    }

    public static <T, R> PageVO<T> from(Page<R> page, Function<? super R, ? extends T> mapper) {
        return PageVO.<T>builder()
                .meta(generateMeta(page))
                .data(page.getContent().stream().map(mapper).collect(toList()))
                .build();
    }
}
