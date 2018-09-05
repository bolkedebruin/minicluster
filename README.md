[![](https://images.microbadger.com/badges/image/bolkedebruin/minicluster.svg)](https://microbadger.com/images/bolkedebruin/minicluster) [![](https://images.microbadger.com/badges/version/bolkedebruin/minicluster.svg)](https://microbadger.com/images/bolkedebruin/minicluster)

# Minicluster

An application to run a minicluster of HDFS, Hive or Hive2 for testing purposes.

## Docker

Simply build the docker container using

```bash
docker build --no-cache -t minicluster .
```

For a Hive cluster, run:

```bash
docker run minicluster hive
```

For a HDFS cluster, run:

```bash
docker run minicluster hdfs
```

To start all services, simply run:

```bash
docker run minicluster
```
