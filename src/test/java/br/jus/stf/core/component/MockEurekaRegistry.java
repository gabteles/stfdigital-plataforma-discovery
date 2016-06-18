package br.jus.stf.core.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

import br.jus.stf.core.components.CommandDto;
import br.jus.stf.core.components.QueryDto;
import br.jus.stf.core.components.RouteDto;

/**
 * @author lucas.rodrigues
 *
 */
@Configuration
public class MockEurekaRegistry {
	
	@Bean
	public PeerAwareInstanceRegistry peerAwareInstanceRegistry() throws JsonProcessingException {
		PeerAwareInstanceRegistry registry = Mockito.mock(PeerAwareInstanceRegistry.class);
		
        Applications apps = Mockito.mock(Applications.class);
        List<Application> registeredApps = mockApplications();
        
        Mockito.when(apps.getRegisteredApplications()).thenReturn(registeredApps);
        Mockito.when(registry.getApplications()).thenReturn(apps);
        
		return registry;
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
	
}