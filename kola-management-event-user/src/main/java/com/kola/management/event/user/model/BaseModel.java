package com.notary.management.kernel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel extends BaseKernelModel{

    @Column()
    private String name;


    public Long baseModelId;
    public BaseModel(String name){
        this.name = name;
    }
}
