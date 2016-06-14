package br.jus.stf.core.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.framework.component.command.CommandConfig;
import br.jus.stf.core.framework.component.navigation.RouteConfig;
import br.jus.stf.core.framework.component.query.QueryConfig;

/**
 * @author lucas.rodrigues
 *
 */
@RestController
@RequestMapping("/api/routes")
public class RouteRestResource {

	@Autowired
	private ComponentServiceFacade componentService;
	
	@ApiOperation("Serviço que agrega as pesquisas de cada serviço")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<RouteConfig> routes() throws Exception {
		List<RouteConfig> routes = new ArrayList<RouteConfig>();
		componentService.list("queries", QueryConfig.class).stream()
			.filter(query -> query.getRoute() != null)
			.forEach(query -> routes.add(((QueryConfig) query).getRoute()));
		componentService.list("commands", CommandConfig.class).stream()
			.filter(command -> command.getRoute() != null)
			.forEach(command -> routes.add(((CommandConfig) command).getRoute()));
		return routes;
	}
	
}
