package com.netcracker.ncedu.tlt.dimi1.expensemanager;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;
    /*private MockMvc mockMvc;

    @LocalServerPort
    private int port;*/

    /*@Test
    public void hello() throws Exception {

        this.mockMvc.perform(get("http://localhost:" + port + "/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }*/

    @Test
    public void helloController(){
        assertThat(helloController).isNotNull();
    }
}