package br.jus.stf.core.components.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.components.ComponentServiceFacade;

/**
 * @author lucas.rodrigues
 *
 */
@RestController
@RequestMapping("/api/queries")
public class QueryRestResource {
	
	@Autowired
	private ComponentServiceFacade componentService;
	
	@ApiOperation("Serviço que agrega as pesquisas de cada serviço")
	@RequestMapping(value = "/searchs", method = RequestMethod.GET, produces = "application/json")
	public List<QueryDto> queries() throws Exception {
		return componentService.list("queries", QueryDto.class).stream()
				.filter(query -> "SEARCH".equals(query.getType()))
				.collect(Collectors.toList());
	}
	
	@ApiOperation("Serviço que agrega as pesquisas de sugestão de cada serviço")
	@RequestMapping(value = "/suggestions", method = RequestMethod.GET, produces = "application/json")
	public List<QueryDto> suggestions() throws Exception {
		return componentService.list("queries", QueryDto.class).stream()
				.filter(query -> "SUGGESTION".equals(query.getType()))
				.collect(Collectors.toList());
	}

}
