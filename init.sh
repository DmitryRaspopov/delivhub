#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<-EOSQL
    -- 1. Сервис авторизации auth-service
    CREATE USER $AUTH_DB_USER WITH PASSWORD '$AUTH_DB_PASSWORD';
    CREATE DATABASE delivery_auth_db OWNER $AUTH_DB_USER;

    -- 2. Сервис каталога товаров store-service
    CREATE USER $STORE_DB_USER WITH PASSWORD '$STORE_DB_PASSWORD';
    CREATE DATABASE delivery_store_db OWNER $STORE_DB_USER;

    -- 3. Сервис клиентов customer-service
    CREATE USER $CUSTOMER_DB_USER WITH PASSWORD '$CUSTOMER_DB_PASSWORD';
    CREATE DATABASE delivery_customer_db OWNER $CUSTOMER_DB_USER;

    -- 4. Сервис заказов и логистики delivery-service
    CREATE USER $ORDER_DB_USER WITH PASSWORD '$ORDER_DB_PASSWORD';
    CREATE DATABASE delivery_order_db OWNER $ORDER_DB_USER;
EOSQL