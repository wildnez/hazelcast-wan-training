package com.hazelcast.training.wan;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.training.wan.util.LicenseUtil;

/**
 * Main class for creating server instance in Cluster A.
 */

public class WanReplicationClusterA {

    public static void main(String[] args) {
        Config config = new Config();
        config.setLicenseKey(LicenseUtil.KEY);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().setPort(5701).setPortAutoIncrement(true).setPortCount(20);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("127.0.0.1");
        config.setClusterName("ClusterA-Name");

        config.addMapConfig(buildMapConfig());
        Hazelcast.newHazelcastInstance(config);
    }

    private static MapConfig buildMapConfig() {
        MapConfig mapConfig = new MapConfig("WAN_MAP");
        mapConfig.setMaxIdleSeconds(120);
        return mapConfig;
    }

}
