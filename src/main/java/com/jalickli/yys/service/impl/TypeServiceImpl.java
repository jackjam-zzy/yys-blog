package com.jalickli.yys.service.impl;

import com.jalickli.yys.entity.Type;
import com.jalickli.yys.mapper.TypeMapper;
import com.jalickli.yys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public void saveType(Type type) {
        typeMapper.saveType(type);
    }

    @Override
    public void updateType(Type type,Long id) {
        typeMapper.updateType(type,id);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }
}
