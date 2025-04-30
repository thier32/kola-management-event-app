package com.notary.management.kernel.services;

import com.notary.management.kernel.exception.KernelException;
import com.notary.management.kernel.model.BaseModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseModelService extends BaseKernelService<BaseModel> {

    @Override
    public BaseModel save(BaseModel entity) throws KernelException {
        entity.setCreatedAt(new Date());
        if (entity.getId() != null){
            entity.setUpdatedAt(new Date());
        }
        return super.save(entity);
    }
}
