package com.mate.util.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToObj(D dto);
}
