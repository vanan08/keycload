package org.keycloak.models.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="moduleHasRole", query="select m from ModuleRoleMappingEntity m where m.roleId = :roleId and module = :module"),
    @NamedQuery(name="selectRolesByUserModule", query="select m from ModuleRoleMappingEntity m where m.module = :module"),
    @NamedQuery(name="selectRolesByRoleId", query="select m from ModuleRoleMappingEntity m where m.module = :module and m.roleId = :roleId"),
    @NamedQuery(name="selectRolesByModule", query="select m from ModuleRoleMappingEntity m where m.module = :module"),
    @NamedQuery(name="deleteModuleRoleMappingByRole", query="delete from ModuleRoleMappingEntity where module = :module and roleId = :roleId"),
    @NamedQuery(name="deleteModuleRoleMappingByUser", query="delete from ModuleRoleMappingEntity where module = :module and roleId = :roleId"),
    @NamedQuery(name="deleteModuleRoleMappingByModule", query="delete from ModuleRoleMappingEntity where module = :module")
})
@Table(name="CUSTOM_PSE_FUNCTIONS_ROLE")
@Entity
/*@IdClass(ModuleRoleMappingEntity.Key.class)*/
public class ModuleRoleMappingEntity {

	@Id
    @Column(name="CUSTOM_PSE_FUNCTIONS_ROLE_ID", length = 36)
    private String id;
	
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CUSTOM_PSE_FUNCTIONS_ID")
    protected ModuleEntity module;

    @Column(name = "ROLE_ID")
    protected String roleId;
    
    @Column(name = "CREATED_BY")
    protected String createdBy;
    
    @Column(name = "CREATED_DATE")
    protected Date createdDate;
    
    @Column(name = "UPDATED_BY")
    protected String updatedBy;
    
    @Column(name = "UPDATED_DATE")
    protected String updatedDate;
	
    public String getId() {
		return id;
	}
    
    public void setId(String id) {
		this.id = id;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
    
	/*public static class Key implements Serializable {

        protected ModuleEntity module;

        protected String roleId;
        
        //protected String userId;

        public Key() {
        }

        public Key(ModuleEntity module, String roleId) {
            this.module = module;
            this.roleId = roleId;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!roleId.equals(key.roleId)) return false;
            if (!module.equals(key.module)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = module.hashCode();
            result = 31 * result + roleId.hashCode();
            return result;
        }
    }*/
	
}