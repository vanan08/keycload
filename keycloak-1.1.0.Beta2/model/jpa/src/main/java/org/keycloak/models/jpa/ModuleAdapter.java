package org.keycloak.models.jpa;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.jpa.entities.ModuleEntity;
import org.keycloak.models.jpa.entities.ModuleRoleMappingEntity;

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
	
	@Override
	public String getActive() {
		return moduleEntity.getActive();
	}

	@Override
	public void setActive(String active) {
		moduleEntity.setActive(active);
	}

	@Override
	public Date getStartDate() {
		return moduleEntity.getStartDate();
	}

	@Override
	public void setStartDate(Date startDate) {
		moduleEntity.setStartDate(startDate);
	}

	@Override
	public Date getEndDate() {
		return moduleEntity.getEndDate();
	}

	@Override
	public void setEndDate(Date endDate) {
		moduleEntity.setEndDate(endDate);
	}

	@Override
	public String getCreatedBy() {
		return moduleEntity.getCreatedBy();
	}

	@Override
	public void setCreatedBy(String createdBy) {
		moduleEntity.setCreatedBy(createdBy);
	}

	@Override
	public Date getCreatedDate() {
		return moduleEntity.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		moduleEntity.setCreatedDate(createdDate);
	}

	@Override
	public String getUpdatedBy() {
		return moduleEntity.getUpdatedBy();
	}

	@Override
	public void setUpdatedBy(String updatedBy) {
		moduleEntity.setUpdatedBy(updatedBy);
	}

	@Override
	public Date getUpdatedDate() {
		return moduleEntity.getUpdatedDate();
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		moduleEntity.setUpdatedDate(updatedDate);
	}

	public ModuleEntity getModuleEntity() {
		return moduleEntity;
	}
	
	@Override
	public List<String> getListRoles(String userId) {
		Set<RoleModel> entities = getRoles(userId);
        List<String> roles = new ArrayList<String>();
        if (entities == null) return roles;
        for (RoleModel entity : entities) {
            roles.add(entity.getName());
        }
        return roles;
	}
	
	@Override
	public RoleModel addRole(String createdBy, String rolename) {
		RoleModel role = applicationAdapter.getRole(rolename);
        
        ModuleRoleMappingEntity moduleRoleMappingEntity = new ModuleRoleMappingEntity();
        moduleRoleMappingEntity.setModule(moduleEntity);
        moduleRoleMappingEntity.setRoleId(role.getId());
        moduleRoleMappingEntity.setCreatedBy(createdBy);
        moduleRoleMappingEntity.setCreatedDate(new Date());
        em.persist(moduleRoleMappingEntity);
        em.flush();
        
        return role;
	}
	
	@Override
	public boolean removeRole(String userId, RoleModel role) {
		if (role == null) {
            return false;
        }
		
		em.createNamedQuery("deleteModuleRoleMappingByUser")
			.setParameter("module", moduleEntity)
			.setParameter("roleId", role.getId())
			.executeUpdate();
		em.flush();
		return true;
	}
	
	@Override
	public void updateModule() {
		em.flush();
	}

	@Override
	public Set<RoleModel> getRoles(String userId) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		
		TypedQuery<ModuleRoleMappingEntity> query = em.createNamedQuery("selectRolesByUserModule", ModuleRoleMappingEntity.class);
		query.setParameter("module", moduleEntity);
	    
	    List<ModuleRoleMappingEntity> ls = query.getResultList();
	    
	    for (ModuleRoleMappingEntity entity : ls) {
	    	roles.add(applicationAdapter.getRoleById(entity.getRoleId()));
	    }
		
		return roles;
	}
	
	@Override
	public boolean container(String userId, RoleModel role) {
		Set<RoleModel> roles = getRoles(userId);
		if (roles.size() == 0) return false;
		
		for (RoleModel rm : roles) {
			if (rm.getId().equals(role.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public RoleModel getRoleByName(String userId, String name) {
	    RoleModel role = applicationAdapter.getRole(name);
	    if (role == null) return null;
	    
	    TypedQuery<ModuleRoleMappingEntity> query = em.createNamedQuery("selectRolesByRoleId", ModuleRoleMappingEntity.class);
		query.setParameter("module", moduleEntity);
	    query.setParameter("roleId", role.getId());
	    
	    List<ModuleRoleMappingEntity> ls = query.getResultList();
	    
	    if (ls.size() == 0) {
	    	return null;
	    }
	    
		return role;
	}

	@Override
	public boolean hasRole(String roleId) {
		TypedQuery<ModuleRoleMappingEntity> query = em.createNamedQuery("moduleHasRole", ModuleRoleMappingEntity.class);
	    query.setParameter("roleId", roleId);
	    query.setParameter("module", moduleEntity);
	    
	    List<ModuleRoleMappingEntity> ls = query.getResultList();
	    if (ls.size() == 0) {
	    	return false;
	    }
	    
		return true;
	}

	@Override
	public boolean removeAllRoles() {
	    em.createNamedQuery("deleteModuleRoleMappingByModule")
	      .setParameter("module", moduleEntity)
	      .executeUpdate();
	    em.flush();
		return true;
	}
	
	@Override
	public Set<RoleModel> getAllRoles() {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		
		TypedQuery<ModuleRoleMappingEntity> query = em.createNamedQuery("selectRolesByModule", ModuleRoleMappingEntity.class);
		query.setParameter("module", moduleEntity);
	    
	    List<ModuleRoleMappingEntity> ls = query.getResultList();
	    
	    for (ModuleRoleMappingEntity entity : ls) {
	    	roles.add(applicationAdapter.getRoleById(entity.getRoleId()));
	    }
		
		return roles;
	}
	
	public static ModuleEntity toModuleEntity(ModuleModel model, EntityManager em) {
        if (model instanceof ModuleAdapter) {
            return ((ModuleAdapter)model).getModuleEntity();
        }
        return em.getReference(ModuleEntity.class, model.getId());
    }

	@Override
	public RoleModel addRole(String rolename) {
		return _addRole(rolename);
	}

	@Override
	public void setRoles(List<String> rolenames) {
		if (rolenames.size() == 0) return;
		for (String name : rolenames) {
			_addRole(name);
		}
	}
	
	protected RoleModel _addRole(String rolename) {
		RoleModel role = applicationAdapter.getRole(rolename);
		if (role == null) {
			throw new IllegalStateException("Role cannot found");
		}
		
		ModuleRoleMappingEntity moduleRoleMappingEntity = new ModuleRoleMappingEntity();
        moduleRoleMappingEntity.setModule(moduleEntity);
        moduleRoleMappingEntity.setRoleId(role.getId());
        moduleRoleMappingEntity.setCreatedDate(new Date());
        em.persist(moduleRoleMappingEntity);
        em.flush();
        
        return role;
	}

	@Override
	public void removeRole(String rolename) {
		_removeRole(rolename);
	}
	
	@Override
	public void removeRoles(List<String> rolenames) {
		if (rolenames.size() == 0) {
			return;
		}
		
		for (String rolename : rolenames) {
			_removeRole(rolename);
		}
	}
	
	protected void _removeRole(String rolename) {
		RoleModel role = applicationAdapter.getRole(rolename);
		if (role == null) {
            throw new IllegalStateException("role \""+rolename+"\" cannot found");
        }
		
		em.createNamedQuery("deleteModuleRoleMappingByRole")
			.setParameter("module", moduleEntity)
			.setParameter("roleId", role.getId())
			.executeUpdate();
		em.flush();
	}
}