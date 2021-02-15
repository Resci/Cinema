package com.mate.util.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
