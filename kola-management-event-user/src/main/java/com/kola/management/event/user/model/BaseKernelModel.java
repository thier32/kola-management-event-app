package com.notary.management.kernel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseKernelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Date createdAt;

    @Column()
    private Date updatedAt;

    @Column()
    private boolean active;

    @Column()
    private String createdBy;

    @Column()
    private String updatedBy;
}
