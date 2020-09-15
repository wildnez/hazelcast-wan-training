# Hazelcast WAN Replication Training
This is to demonstrate WAN Replication across 2 Hazelcast clusters. The objective is to create 2 parallel Hazelcast clusters, send data from Cluster 1 and confirm the data flowing into Cluster 2. Furthermore, make the WAN Replication bi-directional and ensure that the data sent from Cluster 2 is also visible in Cluster 1. This project has 3 components:

* Cluster A
* Cluster B
* Client

### Client

`runClient.sh` starts one instance of Client. Once started, follow the instructions on the screen. 


### Clusters and members

`runServerA.sh` starts one member of Cluster A

`runServerB.sh` starts one member of Cluster B


> **_NOTE: This lab uses Enterprise features, therefore you need a license key to run_**


## Lab Steps

* Configure WAN replication in `hazelcast_A.xml` and `hazelcast_B.xml`.  Initially, have A send to B.  Configure cluster A to use group name "A" and ports 5701,5702,….  Configure cluster B to use group name "B" and ports 5801,5802,...
* Build the project using `mvn clean package -DskipTests`
* Run 2 members in Cluster A by executing `runServerA.sh` twice
* Run 2 members in Cluster B by executing `runServerB.sh` twice
* Run the client by executing `runClient.sh`. Follow the instructions on the screen to connect and send data into Cluster A.  Then connect to cluster B and verify the data is there. 


To make it more interesting, configure bi-directional WAN Replication and ensure that both clusters receive updates from each other.


### Reference

WAN Replication documentation http://docs.hazelcast.org/docs/3.12/manual/html-single/index.html#wan

For detailed reference manual, go to https://hazelcast.org/documentation/
