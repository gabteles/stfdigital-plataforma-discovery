package br.jus.stf.core.components.query;

import br.jus.stf.core.components.navigation.RouteDto;
import br.jus.stf.core.components.navigation.RoutedComponentDto;

/**
 * @author lucas.rodrigues
 *
 */
public class QueryDto implements RoutedComponentDto {

	private String id;
    private String description;
    private String type;
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
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Override
	public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        return id.equals(((QueryDto) o).id);
	}
	
}
