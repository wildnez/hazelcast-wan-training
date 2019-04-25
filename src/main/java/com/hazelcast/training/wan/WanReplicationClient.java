package com.hazelcast.training.wan;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientClasspathXmlConfig;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.config.InvalidConfigurationException;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.training.wan.util.LicenseUtil;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * <h1> Client instance of WAN Replication test </h1>
 *
 * <p> Connect to one of the two clusters and perform distributed Map operations (read/write).
 * To verify data updates from other cluster through WAN Replication, launch another instance
 * of this app and connect to the other cluster. Once connected, perform a read operation on
 * the key that was stored in other cluster.
 * </p>
 *
 * <p> Address of both clusters are hard coded.</p>
 */
public class WanReplicationClient {

    private static IMap MAP;
    private static HazelcastInstance HAZELCAST;

    public static void main(String[] args) {
        System.out.println("Write \"Help\" for the list of available commands:");
        Scanner reader = new Scanner(System.in);
        while (true) {
            System.out.print("Command: ");
            String command = reader.nextLine().toLowerCase();

            if (command.equalsIgnoreCase("help")) {
                printAvailableCommands();
            }

            if (command.equalsIgnoreCase("connect_cluster_a")) {
                connectClusterAandInitialize();
            }

            if (command.equalsIgnoreCase("connect_cluster_b")) {
                connectClusterBandInitialize();
            }

            if (command.startsWith("get")) {
                String key = command.split(" ")[1];
                doReadOnKey(key);
            }

            if (command.startsWith("put")) {
                String key = command.split(" ")[1];
                String value = command.split(" ")[2];
                doDistributedWrite(key, value);
            }

            if (command.equalsIgnoreCase("shutdown")) {
                shutdown();
            }
        }
    }

    private static void shutdown() {
        if (null != HAZELCAST)
            HAZELCAST.shutdown();
        System.exit(0);
    }

    private static void doDistributedWrite(String key, String value) {
        System.out.println("Writing key and value to cluster map");
        System.out.println("Old value: " + MAP.put(key, value));
    }

    private static void doReadOnKey(String key) {
        System.out.println("Reading value for key: " + key + "  Value: " + MAP.get(key));
    }


    private static void connectClusterAandInitialize() {
        connectandInitialize("hazelcast-client_A.xml");
    }

    private static void connectClusterBandInitialize() {
        connectandInitialize("hazelcast-client_B.xml");
    }

    private static void connectandInitialize(String configFile) {
        if (HAZELCAST != null) {
            HAZELCAST.shutdown();
            HAZELCAST = null;
        }

        ClientConfig config;
        try {
            config = new ClientClasspathXmlConfig(configFile);
        } catch (IllegalArgumentException e) {
            System.out.println("Connect Failed.  Could not find " + configFile);
            return;
        } catch(InvalidConfigurationException ic){
            System.out.println("Connect Failed.  " + configFile + " contains invalid configuration");
            return;
        }

        HAZELCAST = HazelcastClient.newHazelcastClient(config);
        MAP = HAZELCAST.getMap("WAN_MAP");
    }

    private static void printAvailableCommands() {
        System.out.println("Available Commands: ");
        System.out.println("1) CONNECT_CLUSTER_A\n"
                + "2) CONNECT_CLUSTER_B\n"
                + "3) PUT [key] [value]\n"
                + "4) GET [key]\n"
                + "5) Shutdown");
    }

    private static boolean isClientConnected() {
        return HAZELCAST != null && HAZELCAST.getCluster() != null;
    }


}
