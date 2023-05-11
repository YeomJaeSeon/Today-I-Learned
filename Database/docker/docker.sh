unzip employees.zip

docker run -d \
  --name mysql-container \
  -p 3399:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=test \
  --mount type=bind,source="$(pwd)"/employees.sql,target=/docker-entrypoint-initdb.d/init.sql \
  mysql:8.0