
docker exec -it postgres2 psql -h 127.0.0.1 -U postgres-user -d customers


{
   "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
   "database.hostname": "postgres2",
   "database.port": "5432",
   "database.user": "postgres-user",
   "database.password": "postgres-pw",
   "database.dbname": "customers",
   "database.server.name": "customers",
   "table.whitelist": "public.customers",
   "transforms": "unwrap",
   "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",
   "transforms.unwrap.drop.tombstones": "false",
   "transforms.unwrap.delete.handling.mode": "rewrite",
   "topic.prefix": "ya_kafka_3_",
   "topic.creation.enable": "true",
   "topic.creation.default.replication.factor": "-1",
   "topic.creation.default.partitions": "-1",
   "skipped.operations": "none",
   "snapshot.mode": "initial",
   "table.include.list": "public.orders,public.users"
 }