package com.netcracker.ncedu.tlt.dimi1.expensemanager;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;
   
    @Test
    public void helloController(){
        assertThat(helloController).isNotNull();
    }
}
