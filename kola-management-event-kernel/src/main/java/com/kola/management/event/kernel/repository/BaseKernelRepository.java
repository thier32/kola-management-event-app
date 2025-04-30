package com.kola.management.event.kernel.repository;

import com.kola.management.event.kernel.model.BaseKernelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseKernelRepository<T extends BaseKernelModel>  extends JpaRepository<T,Long> {
}
