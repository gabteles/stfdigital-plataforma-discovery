package br.jus.stf.core.components.dashboards;

import br.jus.stf.core.components.ComponentDto;

/**
 * Dto representando um dashlet registrado pelos contextos.
 * 
 * @author tomas.godoi
 *
 */
public class DashletDto implements ComponentDto {

	private String id;
	private String nome;
	private String src;
	private String context;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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
