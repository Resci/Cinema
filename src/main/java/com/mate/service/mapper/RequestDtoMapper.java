package com.mate.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToObj(D dto);
}
