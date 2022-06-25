package com.example.dtomapper;

import com.example.dtomapper.repository.AnimalInfo;
import com.example.dtomapper.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DtoMapperApplicationTests {


    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindByName() {
        AnimalInfo animalInfo = animalRepository.findByName("chó");
        System.out.println(animalInfo.getId());
        System.out.println(animalInfo.getName());
        System.out.println(animalInfo.getLegs());
    }
}
