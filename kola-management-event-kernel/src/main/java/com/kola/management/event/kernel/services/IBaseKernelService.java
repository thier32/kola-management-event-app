package com.kola.management.event.kernel.services;

import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.kernel.repository.BaseKernelRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public interface IBaseKernelService<T extends BaseKernelModel,S,D> {
    String entityIdFormat = "%sId";
    <S,D> D mapping (S sourceObject,Class<D> destinationClass) throws KernelException;
    <S,D> D mapping (S sourceObject,D destinationObject) throws KernelException;
    void updateValue(T entity);
    T save(T entity) throws KernelException;
    BaseKernelRepository<T> getDefaultRepository();
}
