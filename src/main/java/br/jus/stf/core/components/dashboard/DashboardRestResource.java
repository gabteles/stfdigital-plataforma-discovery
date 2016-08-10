package br.jus.stf.core.components.dashboard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.components.ComponentServiceFacade;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardRestResource {

	@Autowired
	private ComponentServiceFacade componentService;

	@ApiOperation("Serviço que agrega os dashboards de cada serviço")
	@RequestMapping(method = RequestMethod.GET)
	public List<DashboardDto> dashboards() throws Exception {
		List<DashletDto> dashlets = componentService.list("dashlets", DashletDto.class);
		List<DashboardDto> dashboards = componentService.list("dashboards", DashboardDto.class);
		dashboards.forEach((dashboard) -> {
			dashboard.setDashlets(dashboard
					.getDashletsIds().stream().map(dashletId -> dashlets.stream()
							.filter(dashlet -> dashlet.getId().equals(dashletId)).findFirst())
					.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
		});
		return dashboards;
	}

}
