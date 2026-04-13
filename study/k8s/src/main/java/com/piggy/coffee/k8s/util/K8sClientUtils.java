package com.piggy.coffee.k8s.util;

import com.piggy.coffee.k8s.domain.K8sClientConfigIf;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public final class K8sClientUtils {
    private static volatile KubernetesClient client;

    public static KubernetesClient getClient(K8sClientConfigIf configIf) {

        if (client == null) {
            synchronized (K8sClientUtils.class) {
                if (client == null) {
                    Config config = new ConfigBuilder().withMasterUrl(configIf.getMasterUr()).withNamespace("default")
                            .withTrustCerts(true).withCaCertData(configIf.getCaCertData())
                            .withClientCertData(configIf.getClientCertData())
                            .withClientKeyData(configIf.getClientKeyData()).build();
                    client = new KubernetesClientBuilder().withConfig(config).build();
                }
            }
        }

        return client;
    }

}
