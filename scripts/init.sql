
CREATE DATABASE IF NOT EXISTS keycloak;
CREATE USER IF NOT EXISTS 'keycloak_db_user'@'%' IDENTIFIED BY 'keycloak_db_user_password';
GRANT ALL  ON keycloak.* TO 'keycloak_db_user'@'%';
FLUSH PRIVILEGES;
