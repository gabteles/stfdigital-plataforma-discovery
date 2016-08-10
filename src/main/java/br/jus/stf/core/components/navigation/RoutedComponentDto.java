package br.jus.stf.core.components.navigation;

import br.jus.stf.core.components.ComponentDto;

public interface RoutedComponentDto extends ComponentDto {

	RouteDto getRoute();
	
	void setRoute(RouteDto route);
	
}
