package com.kola.management.event.kernel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
