package br.jus.stf.core.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

import br.jus.stf.core.components.ComponentDto;
import br.jus.stf.core.components.command.CommandDto;
import br.jus.stf.core.components.dashboard.DashboardDto;
import br.jus.stf.core.components.dashboard.DashletDto;
import br.jus.stf.core.components.navigation.RouteDto;
import br.jus.stf.core.components.query.QueryDto;
import br.jus.stf.core.components.security.SecurityChecker;

/**
 * @author lucas.rodrigues
 *
 */
@Configuration
@Profile("test")
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

	@Bean
	public SecurityChecker securityChecker() {
    	SecurityChecker securityChecker = Mockito.mock(SecurityChecker.class);
    	
    	componentes().values().forEach(component -> Mockito.when(securityChecker.hasPermission(component)).thenReturn(true));
    	
		return securityChecker;
    }
    
    private static List<Application> mockApplications() throws JsonProcessingException {
    	InstanceInfo info = Mockito.mock(InstanceInfo.class);
    	Mockito.when(info.getMetadata()).thenReturn(mockMetadata(componentes()));
    	Mockito.when(info.getId()).thenReturn("teste");
    	Mockito.when(info.getAppName()).thenReturn("teste");
    	
    	List<Application> apps = new ArrayList<Application>();
    	Application app = new Application("teste");
    	app.addInstance(info);
    	apps.add(app);
		return apps;
    }
    
    private static Map<String, String> mockMetadata(Map<String, ComponentDto> componentes) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map<String, String> dados = new HashMap<String, String>();
    	dados.put("commands", "[" + mapper.writeValueAsString(componentes.get("comando"))  + "]");
    	dados.put("queries", "[" + mapper.writeValueAsString(componentes.get("query"))  + "]");
    	dados.put("dashlets", "[" + mapper.writeValueAsString(componentes.get("dashlet-01"))  + "]");
    	dados.put("dashboards", "[" + mapper.writeValueAsString(componentes.get("dashboard-01"))  + "]");
    	
		return dados;
    }
	
    private static Map<String, ComponentDto> componentes() {
    	Map<String, ComponentDto> componentes = new HashMap<>();
        
    	RouteDto route = new RouteDto();
    	route.setId("comando");
    	
    	CommandDto command = new CommandDto();
    	command.setId("comando");
    	command.setRoute(route);
    	command.setStartProcess(true);
    	componentes.put("comando", command);
    	
    	QueryDto query = new QueryDto();
    	query.setId("query");
    	query.setType("SEARCH");
    	query.setRoute(route);
    	componentes.put("query", query);
    	
    	DashletDto dashlet = new DashletDto();
    	dashlet.setId("dashlet-01");
    	dashlet.setNome("Dashlet 01");
    	dashlet.setSrc("teste/dashlets");
    	componentes.put("dashlet-01", dashlet);
    	
    	DashboardDto dashboard = new DashboardDto();
    	dashboard.setId("dashboard-01");
    	dashboard.setNome("Dashboard 01");
    	dashboard.setDashletsIds(Arrays.asList("dashlet-01"));
    	componentes.put("dashboard-01", dashboard);
    	
		return componentes;
    }
	
}
