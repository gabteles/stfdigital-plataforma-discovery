package br.jus.stf.core.components.command;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.components.ComponentServiceFacade;

/**
 * Interface rest que informa todos os comandos disponíveis ao usuário
 * 
 * @author lucas.rodrigues
 *
 */
 /*
  * TODO: O ideal é que este serviço ou o contexto de segurança vá diretamente ao "repositório
  * de recursos" da plataforma e retorne diretamente quais os comandos estão disponíveis ao usuário.
  * A implementação atual é temporária e pode ser lenta, pois consulta cada serviço
  */
@RestController
@RequestMapping("/api/commands")
public class CommandRestResource {
	
	@Autowired
	private ComponentServiceFacade componentService;
	
	@ApiOperation("Serviço que agrega os comandos de cada serviço")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<CommandDto> commands() throws Exception {
		return componentService.list("commands", CommandDto.class);
	}
	
	@ApiOperation("Serviço que agrega os comandos que iniciam processos de cada serviço")
	@RequestMapping(value = "/start-process", method = RequestMethod.GET, produces = "application/json")
	public List<CommandDto> startProcessCommands() throws Exception {
		return componentService.list("commands", CommandDto.class).stream()
				.filter(command -> command.isStartProcess())
				.collect(Collectors.toList());
	}
	
}
