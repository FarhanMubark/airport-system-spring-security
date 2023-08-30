package com.example.airportsystemspringsecurity.Repository;


import com.example.airportsystemspringsecurity.Model.Plane;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest // mean this JPA only for testing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaneRepositoryTest {

    @Autowired
    PlaneRepository planeRepository;


        Plane plane1;

        @BeforeEach
        void  setUp(){
            plane1 = new Plane(null, "airbus", "public", 200, null);
        }


    Plane plane;
        @Test
    public void findPlaneByNumber(){
            planeRepository.save(plane1);
            plane = planeRepository.findPlaneByNumber(plane1.getNumber());
            Assertions.assertThat(plane).isEqualTo(plane1);
    }

}
