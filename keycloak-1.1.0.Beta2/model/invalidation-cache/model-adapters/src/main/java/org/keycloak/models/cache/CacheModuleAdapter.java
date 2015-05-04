package org.keycloak.models.cache;

import java.util.List;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.entities.CachedModule;

public class CacheModuleAdapter implements ModuleModel {

	private ModuleModel updated;
	private CachedModule cached;
	
	public CacheModuleAdapter(ModuleModel updated, CachedModule cached) {
		this.updated = updated;
		this.cached = cached;
	}
	
	@Override
	public void updateModule() {
		if (updated != null) updated.updateModule();
	}

	@Override
	public String getId() {
		if (updated != null) return updated.getId();
		return cached.getId();
	}

	@Override
	public String getName() {
		if (updated != null) return updated.getName();
		return cached.getName();
	}

	@Override
	public void setName(String name) {
		if (updated != null) updated.setName(name);
	}

	@Override
	public String getDescription() {
		if (updated != null) return updated.getDescription();
		return cached.getDescription();
	}

	@Override
	public void setDescription(String description) {
		if (updated != null) updated.setDescription(description);
	}

	@Override
	public String getUrl() {
		if (updated != null) return updated.getUrl();
		return cached.getUrl();
	}

	@Override
	public void setUrl(String url) {
		if (updated != null) updated.setUrl(url);
	}

	@Override
	public boolean hasScope(RoleModel role) {
		if (updated != null) return updated.hasScope(role);
		return false;
	}

	@Override
	public void addRole(String rolename) {
		if (updated != null) updated.addRole(rolename);
	}

	@Override
	public void setRoles(String[] roles) {
		if (updated != null) updated.setRoles(roles);
	}

	@Override
	public List<String> getListRoles() {
		if (updated != null) return updated.getListRoles();
        return cached.getRoles();
	}

}
