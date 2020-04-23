package com.bee.master.application.mapper;

import java.util.List;

public interface GenericMapper<S, T> {

    T toTargetType(S source);

    S toSourceType(T target);

    List<T> toTargetType(List<S> sourceList);

    List<S> toSourceType(List<T> targetList);
}
