package com.netcracker.ncedu.tlt.dimi1.demo;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers.BudgetTypeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.SelfDescribing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/
@RunWith(SpringRunner.class)
@WebMvcTest(BudgetTypeController.class)
/*@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class)*/
public class AddBudgetTypeControllerTest {

    @LocalServerPort
    private int port;

    /*@Autowired
    JdbcTemplate jdbcTemplate;
*/

    @Autowired
    private MockMvc mockMvc;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private BudgetTypeController addBudgetTypeController;

    //TODO протестировать сами java-методы, а  не контроллеры. От контроллеров можно получить ответ с кодом 200, означающий запуск контроллера

    //@Test
    @org.junit.Test
    public void addNewBudgetType() throws Exception {
        /*when(addBudgetTypeController.addNewBudgetType("test", false, null)).thenReturn("d");
        this.mockMvc.perform(get("/addNewBudgetType")).andDo(print()).andExpect(status().isOk());
        String url = "http://localhost:" + this.port;
        this.mockMvc.perform(get(url + "/addNewBudgetType")
        .param("name", "TEST")
        .param("required", "false"))
                .andExpect(status().isOk());
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/addNewBudgetType")
                .queryParam("name", "TEST")
                .queryParam("required", false)
                .build().toUri();
        this.restTemplate.getForEntity(uri, Void.class);*/

        mockMvc.perform(get("/addNewBudgetType?name=test&required=false")).andExpect(status().isOk());


        /*BudgetTypeImpl o = mock(BudgetTypeImpl.class);
        //BudgetTypeImpl o = new BudgetTypeImpl(jdbcTemplate);
        o.createUniqId();
        o.setName("TEST");
        o.create();
        when(o.load(o.getBudgetTypeId()+1)).thenReturn(true);
        assertEquals(true, o.load(o.getBudgetTypeId()));*/
        /*boolean res = o.load(o.getBudgetTypeId());
        assertEquals(true, res);*/
    }

    @Test
    public void addBudgetType() {
        /*String url = "http://localhost:" + this.port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/addBudgetType")
                .queryParam("groupId", "1")
                .queryParam("name", "good test")
                .build().toUri();
        this.restTemplate.getForEntity(uri, Void.class);
        uri = UriComponentsBuilder.fromHttpUrl(url).path("/addBudgetType")
                .queryParam("groupId", "5")
                .queryParam("name", "wrong test")
                .build().toUri();
        this.restTemplate.getForEntity(uri, Void.class);*/
    }
}