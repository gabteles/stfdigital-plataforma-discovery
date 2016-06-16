package br.jus.stf.core.component;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.jus.stf.core.integrationtest.ContextInitializer;

/**
 * @author lucas.rodrigues
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"server.port:0"})
@SpringApplicationConfiguration({ContextInitializer.class, MockEurekaRegistry.class})
public class ComponentIntegrationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws JsonProcessingException {
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void commands() throws Exception {
    	mockMvc.perform(get("/api/commands"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(jsonPath("$[0].id", is("comando")));
    }
    
    @Test
    public void queries() throws Exception {
    	mockMvc.perform(get("/api/queries"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(jsonPath("$[0].id", is("query")));
    }
    
    @Test
    public void routes() throws Exception {
    	mockMvc.perform(get("/api/routes"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(jsonPath("$", hasSize(2)));
    }
    
    @Test
    public void startProcessCommands() throws Exception {
    	mockMvc.perform(get("/api/commands/start-process"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(jsonPath("$[0].id", is("comando")));
    }
    
}
