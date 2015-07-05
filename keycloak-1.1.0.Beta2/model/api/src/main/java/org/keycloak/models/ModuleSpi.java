package org.keycloak.models;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

public class ModuleSpi implements Spi {

	@Override
    public String getName() {
        return "module";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return ModuleProvider.class;
    }

    @Override
    public Class<? extends ProviderFactory> getProviderFactoryClass() {
        return ModuleProviderFactory.class;
    }

}
