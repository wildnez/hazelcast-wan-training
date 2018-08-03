# Hazelcast WAN Replication Training
This is to demonstrate WAN Replication across 2 Hazelcast clusters. The objective is to create 2 parallel Hazelcast clusters, send data from Cluster 1 and confirm the data flowing into Cluster 2. Furthermore, make the WAN Replication bi-directional and ensure that the data sent from Cluster 2 is also visible in Cluster 1. This project has 3 components:

* Cluster A
* Cluster B
* Client

This project programmatically configures Hazelcast properties using Hazelcast API, instead of using declarative configuration. For the purpose of training, WAN Replication in not configured or enabled by default. 


### Client

`runClient.sh` starts one instance of Client. Once started, follow the instructions on the screen. Do note that one Client can only connect to one cluster at a time. To connect a Client to the other cluster, start a new instance.


### Clusters and members

`runServerA.sh` starts one member of Cluster A

`runServerB.sh` starts one member of Cluster B


> **NOTE:** *This demo uses Enterprise features, therefore you need a license key to run this demo.*

## Demo

* Insert the license key at [*LicenseUtil*](https://github.com/wildnez/hazelcast-wan-training/blob/master/src/main/java/com/hazelcast/training/wan/util/LicenseUtil.java)
* Build the project using `mvn clean package -DskipTests`
* Run 2 members in Cluster A by executing `runServerA.sh` twice
* Run 2 members in Cluster B by executing `runServerB.sh` twice
* Run 1st instance of client by executing `runClient.sh` once. Follow the instructions on the screen to connect and send data into Cluster A
* Run 2nd instance of client by executing `runClient.sh` again and follow the instructions to connect to Cluster B. This time, try to query the data that was sent from Cluster A

After the first run, Cluster A's updates will not be found in Cluster B 

Shutdown everything - all servers from both clusters and all clients. Configure WAN Replication (uni-directional at this stage), repeat the above process and ensure that Cluster B receives updates from Cluster A.

To make it more interesting, configure bi-directional WAN Replication and ensure that both clusters receive updates from each other.


### Source

WAN Replication documentation http://docs.hazelcast.org/docs/3.10.4/manual/html-single/index.html#wan

For detailed reference manual, go to https://hazelcast.org/documentation/
