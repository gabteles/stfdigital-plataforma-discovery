package br.jus.stf.core.components.command;

import br.jus.stf.core.components.navigation.RouteDto;
import br.jus.stf.core.components.navigation.RoutedComponentDto;

/**
 * Metadados do comando
 * 
 * @author lucas.rodrigues
 *
 */
public class CommandDto implements RoutedComponentDto {

	private String id;
    private String description;
    private Target target;
    private RouteDto route;
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
	
	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	@Override
	public RouteDto getRoute() {
		return route;
	}

	@Override
	public void setRoute(RouteDto route) {
		this.route = route;
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

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public void setContext(String context) {
		this.context = context;
	}
	
	/**
	 * Encapsula as configurações do objeto de domínio alvo do comando
	 */
	public class Target {
		
	    private String type;
	    private String mode;
	    
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		} 
		
	}
	
}
