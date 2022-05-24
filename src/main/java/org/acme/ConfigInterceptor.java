package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigValue;

public class ConfigInterceptor implements ConfigSourceInterceptor {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(ConfigInterceptor.class);

    @ConfigProperty(name = "my.feature.enabled")
    String featureFlag;

    @Override
    public ConfigValue getValue(ConfigSourceInterceptorContext context, String name) {
        if (name.equals("my.feature.delay")) {
            // String featureFlag = ConfigProvider.getConfig().getConfigValue("my.feature.enabled").getValue();
            LOG.infof("feature.enabled is: %s", featureFlag);
            if (!Boolean.parseBoolean(featureFlag)) {
                return context.proceed(name).withValue("off");
            }
        }
        return context.proceed(name);
    }
}
