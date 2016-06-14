package br.jus.stf.core.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.framework.component.query.QueryConfig;

/**
 * @author lucas.rodrigues
 *
 */
@RestController
@RequestMapping("/api")
public class QueryRestResource {

	@Autowired
	private ComponentServiceFacade componentService;
	
	@ApiOperation("Serviço que agrega as pesquisas de cada serviço")
	@RequestMapping(value = "/queries", method = RequestMethod.GET, produces = "application/json")
	public List<QueryConfig> queries() throws Exception {
		return componentService.list("queries", QueryConfig.class);
	}

}
