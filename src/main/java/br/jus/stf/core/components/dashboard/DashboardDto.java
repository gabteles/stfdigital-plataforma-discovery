package br.jus.stf.core.components.dashboard;

import java.util.List;

import br.jus.stf.core.components.ComponentDto;

/**
 * Dto para Dashboard, que é composto por vários dashlets, identificados aqui
 * pelo seu id.
 * 
 * @author Tomas.Godoi
 *
 */
public class DashboardDto implements ComponentDto {

	private String id;
	private String nome;
	private List<String> dashletsIds;
	private List<DashletDto> dashlets;
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

	public List<String> getDashletsIds() {
		return dashletsIds;
	}

	public void setDashletsIds(List<String> dashletsIds) {
		this.dashletsIds = dashletsIds;
	}

	public List<DashletDto> getDashlets() {
		return dashlets;
	}

	public void setDashlets(List<DashletDto> dashlets) {
		this.dashlets = dashlets;
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
        
        return id.equals(((DashboardDto) o).id);
	}
	
}