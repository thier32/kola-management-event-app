package com.kola.management.event.kernel.services;

import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.kernel.repository.BaseKernelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Service
public class BaseKernelService<T extends BaseKernelModel> {

    @Autowired
    public BaseKernelRepository<T> baseKernelRepository;
    public final String entityNotFoundMessageTemplate = "Entity %s with id %s not found";
    public final String findByEntityIdMethodTemplate = "find%sBy%sId";
    public final String errorMessageTemplate = "%s%s";

    public <D> T update(D newEntity, long entityId) throws KernelException {
        Class<T> classValue = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        String className = classValue.getSimpleName();
        String methodName = String.format(findByEntityIdMethodTemplate,className,className);
        T oldEntity  = null;
        Method method = null;
        KernelException eexception = null;
        try{
            method = getDefaultRepository().getClass().getMethod(methodName,long.class);
        }catch (Exception exception){
            eexception = new KernelException(String.format(errorMessageTemplate,methodName, exception.getMessage()));
        }

        if (eexception != null){
            try{
                method = getDefaultRepository().getClass().getMethod(methodName,Long.class);
                eexception = null;
            }catch (Exception exception){
                eexception = new KernelException(String.format(errorMessageTemplate,methodName, exception.getMessage()));
            }
        }

        if (eexception != null){
            throw  eexception;
        }

        try{
            oldEntity =  (T) method.invoke(getDefaultRepository(),entityId);
        }catch (Exception exception){
            throw  new KernelException(String.format(errorMessageTemplate,methodName, exception.getMessage()));
        }

        if (oldEntity == null){
            throw new KernelException(String.format(entityNotFoundMessageTemplate,newEntity.getClass().getSimpleName(), entityId));
        }
        T entityToSave = (T) mapping(newEntity,oldEntity);
        return save(entityToSave);
    }

    public <S,D> D mapping (S sourceObject,Class<D> destinationClass) throws KernelException {
        D result = null;
        try{
            D instanceOf = destinationClass.getConstructor().newInstance();
            result = this.mapping(sourceObject, instanceOf);
        }catch (Exception exception){
          throw new KernelException(exception.getMessage());
        }
        return result;
    }

    public <S,D> D mapping (S sourceObject,D destinationObject) throws KernelException {

        Field[] oldFields = destinationObject.getClass().getDeclaredFields();
        //Field[] newFields = sourceObject.getClass().getDeclaredFields();

        List<?> propertyList = null;
        boolean isObject = false;
        if (sourceObject instanceof Map<?,?>){
            propertyList = new ArrayList<>(((Map)sourceObject).entrySet());
        }else{
            Field[] newFields = sourceObject.getClass().getDeclaredFields();
            isObject = true;
            propertyList = Arrays.stream(newFields).toList();
        }

        for (int i = 0; i<propertyList.size(); i++){
            String name = !isObject ?
                    ((Map.Entry) propertyList.get(i)).getKey().toString()
                    : ((Field)propertyList.get(i)).getName();
            List<Field> fields = Arrays.stream(oldFields).filter( f -> f.getName().equalsIgnoreCase(name))
                    .toList();
            if (fields.isEmpty()) continue;
            Field field = fields.getFirst();
            try {
                Object nValue = null;

                if (!isObject){
                    nValue = ((Map.Entry) propertyList.get(i)).getValue();
                }
                else{
                    Field sField = ((Field)propertyList.get(i));
                    sField.setAccessible(true);
                    nValue = sField.get(sourceObject);
                }
                if (nValue == null) continue;
                field.setAccessible(true);
                Object value = field.get(destinationObject);
                if (nValue == value) continue;
                field.set(destinationObject,nValue);
            }catch (Exception exception){
                throw new KernelException(String.format(errorMessageTemplate,field.getName(),exception.getMessage()));
            }
        }
        return destinationObject;
    }

    public void updateValue(Object e){

    }

    public T save(T entity) throws KernelException {
        entity.setUpdatedAt(new Date());
        if (entity.getId() == null){
//            entity.setCreatedAt(new Date());
//        }else{
            entity = generateEntityId(entity);
            entity.setCreatedAt(new Date());
        }
        return (T) this.getDefaultRepository().save(entity);
    }

    public String entityIdFormat = "%sId";

    public T generateEntityId(T entity) throws KernelException {
        String name = entity.getClass().getSimpleName();
        String lcName = name.toUpperCase().charAt(0)+name.substring(1,name.length());

        String entityId = String.format(entityIdFormat,lcName);
        Field[] fields = entity.getClass().getDeclaredFields();
        boolean hasError = true;
        String errorMessage = String.format(
                "Entity id is not defined. Please check the property %s is defined in class %s",entityId,name);
        for(Field field : fields){
            if (!field.getName().equalsIgnoreCase(entityId)){
                continue;
            }
            field.setAccessible(true);
            Long id = System.currentTimeMillis();
            try{
                field.set(entity,id);
                hasError = false;
                break;
            }catch (Exception e){
                throw new KernelException(errorMessage);
            }
        }
        if (hasError){
            throw new KernelException(errorMessage);
        }
        return entity;
    }


    public BaseKernelRepository<T> getDefaultRepository(){
        return baseKernelRepository;
    }
}
