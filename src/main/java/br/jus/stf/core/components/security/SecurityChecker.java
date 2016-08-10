package br.jus.stf.core.components.security;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import br.jus.stf.core.components.ComponentDto;

/**
 * Classe responsável por verificar se o usuário possui as permissões necessárias
 * para acessar um componente.
 * 
 * @author Lucas Rodrigues
 * @author Rodrigo Barreiros
 *
 * @since 1.0.0
 * @since 25.11.2015
 */
@Component("securityChecker")
public class SecurityChecker {
	
	/**
	 * Verifica se o usuário corrente possui permissão para acesso a 
	 * um dado componente.
	 * 
	 * @param componente o componente a ser avalidado
	 * @return true, se o usuário possui permissão de acesso
	 * de um componente, false, caso contrário
	 */
	public boolean hasPermission(ComponentDto componente) {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
			.filter(OAuth2Authentication.class::isInstance)
			.map(OAuth2Authentication.class::cast)
			.map(auth -> auth.getUserAuthentication().getDetails())
			.map(Map.class::cast)
			.map(principal -> principal.get("componentes"))
			.map(List.class::cast)
			.map(componentes -> componentes.contains(componente.getId()))
			.orElse(false);
	}
	
}
