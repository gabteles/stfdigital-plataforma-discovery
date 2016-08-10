package br.jus.stf.core.components.navigation;

/**
 * @author lucas.rodrigues
 *
 */
public class RouteDto {

	private String id;
    private String stateName;
    private String navigationItem;
    private String translation;
    private String url;
    private String urlPrefix;
    private String src;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
    
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrlPrefix() {
		return urlPrefix;
	}
	
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
	public String getSrc() {
		return src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getNavigationItem() {
		return navigationItem;
	}
	
	public void setNavigationItem(String navigationItem) {
		this.navigationItem = navigationItem;
	}
	
	public String getTranslation() {
		return translation;
	}
	
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
}
