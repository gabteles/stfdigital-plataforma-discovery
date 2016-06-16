package br.jus.stf.core.components;

/**
 * @author lucas.rodrigues
 *
 */
public interface ComponentDto {

	RouteDto getRoute();
	
	void setRoute(RouteDto route);
	
	String getContext();
	
	void setContext(String context);
	
}
