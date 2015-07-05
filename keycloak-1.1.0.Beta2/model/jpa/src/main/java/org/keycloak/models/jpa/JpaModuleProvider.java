package org.keycloak.models.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.keycloak.models.ModuleModel;
import org.keycloak.models.ModuleProvider;
import org.keycloak.models.jpa.entities.ModuleEntity;

public class JpaModuleProvider implements ModuleProvider {

	protected EntityManager em;
	
	public JpaModuleProvider(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public ModuleModel getModuleByName(String name) {
		TypedQuery<ModuleEntity> query = em.createNamedQuery("getModuleByName", ModuleEntity.class);
        query.setParameter("name", name);
        List<ModuleEntity> entities = query.getResultList();
        if (entities.size() == 0) return null;
        
        ApplicationModuleAdapter.ApplicationInfo app =
        		new ApplicationModuleAdapter.ApplicationInfo(entities.get(0).getApplication());
        
        return new ApplicationModuleAdapter(entities.get(0), app);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

}
