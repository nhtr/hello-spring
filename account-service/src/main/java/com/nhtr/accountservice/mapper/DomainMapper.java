package com.nhtr.accountservice.mapper;

import java.util.List;

public interface DomainMapper<T, D> {
    D toDomain(T dto);

    T toDTO(D domain);

    List<T> toDTOs(List<D> domains);

    List<D> toDomains(List<T> dtoList);
}
