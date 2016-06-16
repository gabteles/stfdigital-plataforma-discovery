package br.jus.stf.core.component;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

import br.jus.stf.core.components.CommandDto;
import br.jus.stf.core.components.QueryDto;
import br.jus.stf.core.components.RouteDto;
import br.jus.stf.core.integrationtest.ContextInitializer;

/**
 * @author lucas.rodrigues
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest({"server.port:0"})
@SpringApplicationConfiguration(ContextInitializer.class)
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
    
    private static List<Application> mockApplications() throws JsonProcessingException {
    	InstanceInfo info = Mockito.mock(InstanceInfo.class);
    	Mockito.when(info.getMetadata()).thenReturn(mockMetadata());
    	Mockito.when(info.getId()).thenReturn("teste");
    	Mockito.when(info.getAppName()).thenReturn("teste");
    	
    	List<Application> apps = new ArrayList<Application>();
    	Application app = new Application("teste");
    	app.addInstance(info);
    	apps.add(app);
		return apps;
    }
    
    private static Map<String, String> mockMetadata() throws JsonProcessingException {
    	RouteDto route = new RouteDto();
    	route.setId("comando");
    	
    	CommandDto command = new CommandDto();
    	command.setId("comando");
    	command.setRoute(route);
    	command.setStartProcess(true);
    	
    	QueryDto query = new QueryDto();
    	query.setId("query");
    	query.setRoute(route);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map<String, String> dados = new HashMap<String, String>();
    	dados.put("commands", "[" + mapper.writeValueAsString(command)  + "]");
    	dados.put("queries", "[" + mapper.writeValueAsString(query)  + "]");
    	
		return dados;
    }
    
    @Configuration
    static class Conf {
    	
    	@Bean
    	public PeerAwareInstanceRegistry peerAwareInstanceRegistry() throws JsonProcessingException {
    		PeerAwareInstanceRegistry registry = Mockito.mock(PeerAwareInstanceRegistry.class);
    		
            Applications apps = Mockito.mock(Applications.class);
            List<Application> registeredApps = mockApplications();
            
            Mockito.when(apps.getRegisteredApplications()).thenReturn(registeredApps);
            Mockito.when(registry.getApplications()).thenReturn(apps);
            
    		return registry;
    	}
    	
    }
    
}
