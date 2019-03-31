package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.SelfDescribing;

//import org.hamcrest.SelfDescribing;

/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/
/*@RunWith(SpringRunner.class)
@WebMvcTest(BudgetTypeController.class)*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class AddBudgetTypeControllerTest {

    /*@LocalServerPort
    private int port;*/

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BudgetTypeController obj;
    private Integer lastId;

    @Before
    public void setJdbcTemplate(){
        obj = new BudgetTypeController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
    }

    /*@Autowired
    private MockMvc mockMvc;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private BudgetTypeController addBudgetTypeController;*/

    //@Test
    @org.junit.Test
    public void addNewBudgetType() throws Exception {
        obj.addNewBudgetType("GOOD TEST", null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT name FROM budget_type " +
                "WHERE budget_type_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST");
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

        /*mockMvc.perform(get("/addNewBudgetType?name=test&required=false")).andExpect(status().isOk());*/


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
        obj.addBudgetType(4, "GOOD TEST2", null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT name FROM budget_type " +
                "WHERE budget_type_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST2");
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