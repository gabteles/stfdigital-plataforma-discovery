package br.jus.stf.core.components;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

import br.jus.stf.core.framework.component.ComponentConfig;

/**
 * Interface rest que informa todos os componentes disponíveis ao usuário
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class ComponentServiceFacade {
	
	private PeerAwareInstanceRegistry registry;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	public ComponentServiceFacade(EurekaServerContext context) {
		registry = context.getRegistry();
	}

	/**
	 * Consulta cada serviço
	 * 
	 * @param metadataName
	 * @param configClass
	 * @return lista de componentes
	 * @throws Exception 
	 */
	//TODO: Filtrar apenas os componentes permitidos para o usuário com @SecuredResource
	@SuppressWarnings("unchecked")
	public <T extends ComponentConfig> List<T> list(String metadataName, Class<T> configClass) throws Exception {
		return (List<T>) registry.getApplications().getRegisteredApplications().stream()
			.flatMap(app -> componentsByApplication(app, metadataName, configClass).stream())
			.collect(Collectors.toList());
	}

	/**
	 * Consulta a primeira instância de cada serviço encontrado
	 * 
	 * @param serviceId
	 * @param metadataName
	 * @param configClass
	 * @return lista de componentes
	 */
	private List<ComponentConfig> componentsByApplication(Application app, String metadataName, Class<?> configClass) {
		return app.getInstances().stream().findAny()
			.map(instance -> componentsByInstance(instance, metadataName, configClass))
			.orElse(Collections.emptyList());
	}

	/**
	 * Consulta uma instância e converte os metatados em componentes
	 * 
	 * @param instance
	 * @param metadataName
	 * @param configClass
	 * @return lista de componentes
	 */
	@SuppressWarnings("unchecked")
	private List<ComponentConfig> componentsByInstance(InstanceInfo instance, String metadataName, Class<?> configClass) {
		return Optional.ofNullable(instance.getMetadata().get(metadataName))
			.map(components -> {
				try {
					CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, configClass);
					return (List<ComponentConfig>) mapper.readValue(components, type);
				} catch (Exception e) {
					throw new RuntimeException("Erro ao carregar componentes.", e);
				}	
			})
			.orElse(Collections.emptyList());
	}
	
}
