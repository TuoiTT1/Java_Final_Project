package com.vn.book_store_server.service.mapper;

import java.util.List;

public interface EntityMapper <D, M>{
    D toDto(M model);
    M toModel(D dto);
//    List<D> toDto(List<M> modelList);
//    List<M> toModel(List<D> dtoList);
}
