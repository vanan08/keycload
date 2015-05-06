package org.keycloak.models.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.jpa.entities.ModuleEntity;
import org.keycloak.models.jpa.entities.RoleEntity;

public class ModuleAdapter implements ModuleModel {

	protected RealmModel realm;
	protected EntityManager em;
	protected ModuleEntity moduleEntity;
	protected ApplicationAdapter applicationAdapter;
	
	public ModuleAdapter(RealmModel realm, EntityManager em, ModuleEntity moduleEntity, ApplicationAdapter applicationAdapter) {
		this.realm = realm;
		this.em = em;
		this.moduleEntity = moduleEntity;
		this.applicationAdapter = applicationAdapter;
	}
	
	@Override
	public String getId() {
		return moduleEntity.getId();
	}
	
	@Override
	public String getName() {
		return moduleEntity.getName();
	}

	@Override
	public void setName(String name) {
		moduleEntity.setName(name);
	}

	@Override
	public String getDescription() {
		return moduleEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		moduleEntity.setDescription(description);
	}

	@Override
	public String getUrl() {
		return moduleEntity.getUrl();
	}

	@Override
	public void setUrl(String url) {
		moduleEntity.setUrl(url);
	}
	
	public ModuleEntity getModuleEntity() {
		return moduleEntity;
	}
	
	@Override
	public List<String> getListRoles() {
		Collection<RoleEntity> entities = moduleEntity.getRoles();
        List<String> roles = new ArrayList<String>();
        if (entities == null) return roles;
        for (RoleEntity entity : entities) {
            roles.add(entity.getName());
        }
        return roles;
	}
	
	@Override
	public void addRole(String rolename) {
		RoleModel role = applicationAdapter.getRole(rolename);
        
        Collection<RoleEntity> entities = moduleEntity.getRoles();
        for (RoleEntity entity : entities) {
            if (entity.getId().equals(role.getId())) {
                return;
            }
        }
        RoleEntity roleEntity = RoleAdapter.toRoleEntity(role, em);
        entities.add(roleEntity);
        em.flush();
	}
	
	@Override
	public void setRoles(String[] rolenames) {
		Collection<RoleEntity> entities = moduleEntity.getRoles();
        Set<String> already = new HashSet<String>();
        List<RoleEntity> remove = new ArrayList<RoleEntity>();
        for (RoleEntity rel : entities) {
            if (!ApplicationAdapter.contains(rel.getName(), rolenames)) {
                remove.add(rel);
            } else {
                already.add(rel.getName());
            }
        }
        for (RoleEntity entity : remove) {
            entities.remove(entity);
        }
        em.flush();
        for (String roleName : rolenames) {
            if (!already.contains(roleName)) {
                addRole(roleName);
            }
        }
        em.flush();
	}
	
	@Override
	public boolean hasScope(RoleModel role) {
		if (applicationAdapter.hasScope(role)) {
            return true;
        }
        Set<RoleModel> roles = applicationAdapter.getRoles();
        if (roles.contains(role)) return true;

        for (RoleModel mapping : roles) {
            if (mapping.hasRole(role)) return true;
        }
        return false;
	}
	
	@Override
	public void updateModule() {
		em.flush();
	}
}
