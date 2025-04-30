package com.kola.management.event.kernel.services;

import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.model.BaseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class BaseModelServiceIntegrationTest {

    @Autowired
    BaseModelService baseModelService;


    @Test
    void givenNewBaseModel_whenSave_thenSuccess() throws KernelException {
        BaseModel newBaseModel = new BaseModel("test");
        BaseModel insertedBaseModed = baseModelService.save(newBaseModel);
        assertThat(insertedBaseModed.getId() != null);
    }

    @Test
    void givenNewBaseModel_whenUpdate_thenSuccess() throws KernelException {
        BaseModel newBaseModel = new BaseModel("test");
        BaseModel insertedBaseModed = baseModelService.save(newBaseModel);
        insertedBaseModed.setName("test3");
        insertedBaseModed = baseModelService.save(insertedBaseModed);
        assertThat(insertedBaseModed.getCreatedAt() != insertedBaseModed.getUpdatedAt());
    }

}
