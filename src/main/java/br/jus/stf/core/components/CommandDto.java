package br.jus.stf.core.components;

/**
 * @author lucas.rodrigues
 *
 */
public class CommandDto implements ComponentDto {

	private String id;
    private String description;
    private RouteDto route;
    private String targetType;
    private boolean listable;
    private boolean startProcess;
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
	
	public RouteDto getRoute() {
		return route;
	}

	public void setRoute(RouteDto route) {
		this.route = route;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public boolean isListable() {
		return listable;
	}

	public void setListable(boolean listable) {
		this.listable = listable;
	}

	public boolean isStartProcess() {
		return startProcess;
	}
	
	public void setStartProcess(boolean startProcess) {
		this.startProcess = startProcess;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
}
