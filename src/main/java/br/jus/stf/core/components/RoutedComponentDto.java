package br.jus.stf.core.components;

public interface RoutedComponentDto extends ComponentDto {

	RouteDto getRoute();
	
	void setRoute(RouteDto route);
	
}
