package org.keycloak.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="moduleHasRole", query="select m from ModuleRoleMappingEntity m where m.roleId = :roleId"),
	@NamedQuery(name="selectRolesByUser", query="select m from ModuleRoleMappingEntity m where m.userId = :userId"),
    @NamedQuery(name="selectRolesByUserModule", query="select m from ModuleRoleMappingEntity m where m.module = :module and m.userId = :userId"),
    @NamedQuery(name="selectRolesByRoleId", query="select m from ModuleRoleMappingEntity m where m.module = :module and m.roleId = :roleId and m.userId = :userId"),
    @NamedQuery(name="selectRolesByModule", query="select m from ModuleRoleMappingEntity m where m.module = :module"),
    @NamedQuery(name="deleteModuleRoleMappingByRole", query="delete from ModuleRoleMappingEntity where module = :module and roleId = :roleId"),
    @NamedQuery(name="deleteModuleRoleMappingByUser", query="delete from ModuleRoleMappingEntity where module = :module and roleId = :roleId and userId = :userId"),
    @NamedQuery(name="deleteModuleRoleMappingByModule", query="delete from ModuleRoleMappingEntity where module = :module")
})
@Table(name="MODULE_ROLE_MAPPING")
@Entity
@IdClass(ModuleRoleMappingEntity.Key.class)
public class ModuleRoleMappingEntity {

	@Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    protected ModuleEntity module;

    @Id
    @Column(name = "ROLE_ID")
    protected String roleId;
    
    @Id
    @Column(name = "USER_ID")
    protected String userId;
	
    public ModuleEntity getModule() {
		return module;
	}
    
    public void setModule(ModuleEntity module) {
		this.module = module;
	}
    
    public String getRoleId() {
		return roleId;
	}
    
    public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
    public String getUserId() {
		return userId;
	}
    
    public void setUserId(String userId) {
		this.userId = userId;
	}
    
	public static class Key implements Serializable {

        protected ModuleEntity module;

        protected String roleId;
        
        protected String userId;

        public Key() {
        }

        public Key(ModuleEntity module, String roleId, String userId) {
            this.module = module;
            this.roleId = roleId;
            this.userId = userId;
        }

        public ModuleEntity getModule() {
			return module;
		}
        
        public void setModule(ModuleEntity module) {
			this.module = module;
		}
        
        public String getRoleId() {
			return roleId;
		}
        
        public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
        
        public String getUserId() {
			return userId;
		}
        
        public void setUserId(String userId) {
			this.userId = userId;
		}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!roleId.equals(key.roleId)) return false;
            if (!module.equals(key.module)) return false;
            if (!userId.equals(key.userId)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = module.hashCode();
            result = 31 * result + roleId.hashCode() + userId.hashCode();
            return result;
        }
    }
	
}
