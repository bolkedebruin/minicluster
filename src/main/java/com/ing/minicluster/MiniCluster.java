package com.ing.minicluster;

import com.github.sakserv.minicluster.impl.HdfsLocalCluster;
import com.github.sakserv.minicluster.impl.HiveLocalMetaStore;
import com.github.sakserv.minicluster.impl.HiveLocalServer2;
import com.github.sakserv.minicluster.impl.ZookeeperLocalCluster;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;

public class MiniCluster {
    public static void zookeeperMinicluster() throws Exception {
        ZookeeperLocalCluster zookeeperLocalCluster = new ZookeeperLocalCluster.Builder()
            .setPort(2181)
            .setTempDir("embedded_zookeeper")
            .setZookeeperConnectionString("localhost:2181")
            .build();

        zookeeperLocalCluster.start();
    }

    public static void hdfsMinicluster() throws Exception {
        HdfsLocalCluster hdfsLocalCluster = new HdfsLocalCluster.Builder()
                .setHdfsNamenodePort(8020)
                .setHdfsNamenodeHttpPort(50070)
                .setHdfsTempDir("embedded_hdfs")
                .setHdfsNumDatanodes(1)
                .setHdfsEnablePermissions(false)
                .setHdfsFormat(true)
                .setHdfsEnableRunningUserAsProxyUser(true)
                .setHdfsConfig(new Configuration())
                .build();

        hdfsLocalCluster.start();
    }

    public static void hiveServer2MiniCluster() throws Exception {
        HiveLocalMetaStore hiveLocalMetaStore = new HiveLocalMetaStore.Builder()
                .setHiveMetastoreHostname("localhost")
                .setHiveMetastorePort(9083)
                .setHiveMetastoreDerbyDbDir("metastore_db")
                .setHiveScratchDir("hive_scratch_dir")
                .setHiveWarehouseDir("warehouse_dir")
                .setHiveConf(new HiveConf())
                .build();
        hiveLocalMetaStore.start();

        HiveLocalServer2 hiveLocalServer2 = new HiveLocalServer2.Builder()
                .setHiveServer2Hostname("localhost")
                .setHiveServer2Port(10000)
                .setHiveMetastoreHostname("localhost")
                .setHiveMetastorePort(9083)
                .setHiveMetastoreDerbyDbDir("metastore_db")
                .setHiveScratchDir("hive_scratch_dir")
                .setHiveWarehouseDir("warehouse_dir")
                .setHiveConf(new HiveConf())
                .setZookeeperConnectionString("localhost:2181")
                .build();

        hiveLocalServer2.start();
    }

    public static void main(String[] args) {
        try {
            zookeeperMinicluster();
            hdfsMinicluster();
            hiveServer2MiniCluster();
        } catch (Exception e) {
            System.out.println("Error while starting: " + e);
        }
    }
}
