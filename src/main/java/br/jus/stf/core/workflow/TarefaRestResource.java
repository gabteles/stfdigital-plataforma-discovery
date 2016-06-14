package br.jus.stf.core.workflow;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.PortType;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 23.06.2015
 */
@RestController
@RequestMapping("/api/tarefas")
public class TarefaRestResource {
    
	private PeerAwareInstanceRegistry registry;
    
	@Autowired
	private RestTemplate restTemplate;   
	
	@Autowired
	public TarefaRestResource(EurekaServerContext context) {
		registry = context.getRegistry();
	}
	
	@Autowired
	DiscoveryClient discoveryClient;
	
    @ApiOperation(value = "Lista todas as tarefas associadas ao usuário corrente")
	@RequestMapping(method = GET, produces = "application/json")
	public List<TarefaDto> tarefas() {
    	return registry.getApplications().getRegisteredApplications().parallelStream()
    		.flatMap(servico -> tarefas(servico).stream())
    		.collect(Collectors.toList());
	}

    @SuppressWarnings("unchecked")
	private List<TarefaDto> tarefas(Application application) {
		 return application.getInstances().stream()
			.findAny()
			.filter(instance -> instance.getMetadata().get("hasApiTarefas").equals("true"))
			.map(instance -> restTemplate.getForObject(getURI(instance), List.class)) 
			.orElse(Collections.emptyList());
	}

	/**
	 * @param instance
	 * @return
	 */
	private URI getURI(InstanceInfo instance) {
		String serviceUri = String.format("%s://%s:%s", 
				(instance.isPortEnabled(PortType.SECURE)) ? "https" : "http", instance.getHostName(), instance.getPort());
		return UriComponentsBuilder.fromUriString(serviceUri).path("/api/tarefas").build().toUri();
	}
    
}
