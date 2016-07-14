package br.jus.stf.core.components;

/**
 * @author lucas.rodrigues
 *
 */
public class QueryDto implements RoutedComponentDto {

	private String id;
    private String description;
    private RouteDto route;
    private String context;
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
	
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
    	this.description = description;
    }
	
    @Override
	public RouteDto getRoute() {
		return route;
	}

    @Override
	public void setRoute(RouteDto route) {
		this.route = route;
	}

    @Override
	public String getContext() {
		return context;
	}

	@Override
	public void setContext(String context) {
		this.context = context;
	}
	
}
