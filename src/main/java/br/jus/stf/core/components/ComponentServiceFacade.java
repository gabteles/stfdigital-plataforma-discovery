package br.jus.stf.core.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

/**
 * Interface rest que informa todos os componentes disponíveis ao usuário
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class ComponentServiceFacade {
	
	@Autowired
	private PeerAwareInstanceRegistry registry;
	
	private ObjectMapper mapper;
	
	public ComponentServiceFacade() {
		 mapper = new ObjectMapper();
		 mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
	public <T extends ComponentDto> List<T> list(String metadataName, Class<T> compClass) throws Exception {
		return (List<T>) registry.getApplications().getRegisteredApplications().stream()
			.flatMap(app -> componentsByApplication(app, metadataName, compClass).stream())
			.collect(Collectors.toList());
	}

	/**
	 * Consulta a primeira instância de cada serviço encontrado
	 * 
	 * @param app
	 * @param metadataName
	 * @param compClass
	 * @return lista de componentes
	 */
	private List<ComponentDto> componentsByApplication(Application app, String metadataName, Class<?> compClass) {
		return app.getInstances().stream().findAny()
			.map(instance -> componentsByInstance(instance, metadataName, compClass))
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
	private List<ComponentDto> componentsByInstance(InstanceInfo instance, String metadataName, Class<?> configClass) {
		return Optional.ofNullable(instance.getMetadata().get(metadataName))
			.map(components -> {
				try {
					CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, configClass);
					List<ComponentDto> ctxComponents = (List<ComponentDto>) mapper.readValue(components, type);
					ctxComponents.stream().forEach(ctxComponent -> ctxComponent.setContext(instance.getAppName().toLowerCase()));
					return ctxComponents;
				} catch (Exception e) {
					throw new RuntimeException("Erro ao carregar componentes.", e);
				}	
			})
			.orElse(Collections.emptyList());
	}
	
}
