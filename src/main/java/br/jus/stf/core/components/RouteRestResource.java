package br.jus.stf.core.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author lucas.rodrigues
 *
 */
@RestController
@RequestMapping("/api/routes")
public class RouteRestResource {

	@Autowired
	private ComponentServiceFacade componentService;
	
	@ApiOperation("Serviço que agrega as rotas de cada serviço")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<RouteDto> routes() throws Exception {
		List<RouteDto> routes = new ArrayList<RouteDto>();
		routes.addAll(getRoutes("commands", CommandDto.class));
		routes.addAll(getRoutes("queries", QueryDto.class));
		return routes;
	}

	/**
	 * @param routes
	 * @throws Exception
	 */
	private List<RouteDto> getRoutes(String metadataName, Class<? extends ComponentDto> ctxClass ) throws Exception {
		return componentService.list(metadataName, ctxClass).stream()
			.filter(component -> component.getRoute() != null)
			.map(component -> component.getRoute())
			.collect(Collectors.toList());
	}
	
}
