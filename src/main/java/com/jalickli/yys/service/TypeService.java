package com.jalickli.yys.service;

import com.jalickli.yys.entity.Type;

import java.util.List;

public interface TypeService {

    Type getTypeById(Long id);
    void saveType(Type type);
    List<Type> listType();
    void updateType(Type type,Long id);
    void deleteType(Long id);
}
