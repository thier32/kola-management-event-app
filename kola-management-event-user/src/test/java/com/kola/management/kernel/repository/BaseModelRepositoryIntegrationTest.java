package com.kola.management.event.user.repository;

import com.kola.management.event.user.model.BaseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class BaseModelRepositoryIntegrationTest {

    @Autowired
    BaseModelRepository baseModelRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenNewBaseModel_whenSave_thenSuccess() {
        BaseModel newBaseModel = new BaseModel("test");

        BaseModel insertedBaseModed = baseModelRepository.save(newBaseModel);
        assertThat(entityManager.find(BaseModel.class, insertedBaseModed.getId()) ).isEqualTo(newBaseModel);
    }


    @Test
    void givenBaseModelCreated_whenUpdate_thenSuccess() {
        BaseModel newBaseModel = new BaseModel("test");
        entityManager.persist(newBaseModel);
        String newName = "New Campaign 001";
        newBaseModel.setName(newName);
        baseModelRepository.save(newBaseModel);
        assertThat(entityManager.find(BaseModel.class, newBaseModel.getId()).getName()).isEqualTo(newName);
    }

    @Test
    void givenBaseModelCreated_whenFindById_thenSuccess() {
        BaseModel newBaseModel = new BaseModel("CTEST-1");
        entityManager.persist(newBaseModel);
        Optional<BaseModel> retrievedBaseModel = baseModelRepository.findById(newBaseModel.getId());
        assertThat(retrievedBaseModel).contains(newBaseModel);
    }
}
