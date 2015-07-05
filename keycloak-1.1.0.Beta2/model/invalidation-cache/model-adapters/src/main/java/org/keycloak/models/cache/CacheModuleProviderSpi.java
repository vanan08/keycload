package org.keycloak.models.cache;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

public class CacheModuleProviderSpi implements Spi {

	@Override
    public String getName() {
        return "moduleCache";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return CacheModuleProvider.class;
    }

    @Override
    public Class<? extends ProviderFactory> getProviderFactoryClass() {
        return CacheModuleProviderFactory.class;
    }

}
