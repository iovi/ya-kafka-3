ДЛЯ ЗАПУСКА

Требуется установленный docker и java 17+

Необходимо 
- запустить docker-compose.yml с помощью команды "docker-compose.yml up -d"
- создать таблицы users и orders в развёрнутой БД.
  Для подключения выполнить
> docker exec -it postgres2 psql -h 127.0.0.1 -U postgres-user -d customers

Для создания выполнить
>CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(100),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE orders (
id SERIAL PRIMARY KEY,
user_id INT REFERENCES users(id),
product_name VARCHAR(100),
quantity INT,
order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

- сконфигурировать Debezeum, отправив PUT-запрос на адрес http://localhost:8083/connectors/pg-connector/config с указанным телом
    {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector", //название коннектора
    "database.hostname": "postgres2", //имя хоста, задано в docker-compose.yaml
    "database.port": "5432", //порт для подключения, задано в docker-compose.yaml
    "database.user": "postgres-user", //пользователь БД
    "database.password": "postgres-pw", //пароль пользователя БД
    "database.dbname": "customers",  //название БД
    "database.server.name": "customers", //название сервера БД
    "table.include.list": "public.orders,public.users", //перечень таблиц, изменения в которых отслеживаются
    "transforms": "unwrap", //тип преобразования данных в коннекторе
    "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState", //изменения в таблицах преобразовываются в строки
    "transforms.unwrap.drop.tombstones": "false", // показываем удаления строк
    "topic.prefix": "ya_kafka_3", //префикс названий топиков, в которые пойдёт запись об изменениях таблиц 
    "topic.creation.enable": "true", // создавать топики автоматически
    "topic.creation.default.replication.factor": "-1", // количество реплик, установленных в Kafka по умолчанию
    "topic.creation.default.partitions": "-1", // количество партиций, установленных в Kafka по умолчанию
    "skipped.operations": "none", //никакие операции с таблицами не пропускаются
    }
Комментарии из json необходимо удалить перед отправкой.

- Запустить jar файл командой "java -jar out/artifacts/ya_kafka_3_jar/ya-kafka-3.jar"

- Изменить данные в таблицах. Эти изменения приложение выведет в консоль
