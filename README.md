# Favorite products 

* Conjunto de APIs que visa administrar os produtos de clientes
* Este projeto procura respeitar arquitetura de micro-serviços, para que desta forma tenhamos uma solução escalavel, rápida e de fácil manutenção
* Obs: Esse projeto foca no desenvolvimento do backend e para fins de desenvolvimento, não foi criado o Load Balancer e não foi criado um cluster de filas

# Tecnologias utilizadas nesse projeto

* Java 
* Apache Kafka 
* Maven 
* MySql
* Redis
* Spring 
* Docker
* Kafka 

# Pré requisitos para debug

* Para testar esse projeto, você deverá utilizar

1. JDK com a versão mais recente instalada localmente 
2. Docker instalando/executando localmente 
3. Eclipse com plugin Lombok instalado

# Documentação das APIs 
  * (API de persistência de Clientes) 

        `http://localhost:8080/swagger-ui.html`

  * (API de persistência de Produtos) 

        `http://localhost:8082/swagger-ui.html`

  * (API de consulta de Clientes) 

        `http://localhost:8081/swagger-ui.html`




# Como executar o projeto em modo debug 

1. Subindo os containers
  * Rode os comandos que seguem no terminal 
  * Vá até o diretorio aonde se encontra o docker-compose.yaml e rode o comando 
  
        `docker run --name kafka_stream_data -p 9092:9092 kafka`

        `docker run --name kafka_stream_product -p 9093:9093 kafka`
  
  * suba os BDs execute 
  
        `docker run --rm -d -e MYSQL_ROOT_PASSWORD=root -p 3325:3306 --name customers_base_sql mysql:5.7`

	* Conecte-se na base e rode o seguinte script 

		`CREATE DATABASE customer	_base`
  
        `docker run --rm -d -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 --name products_base_sql mysql:5.7`
  
	* Conecte-se na base e rode o seguinte script 

		`CREATE DATABASE product_base`

  * suba os BDs de cache 

        `docker run -it --name customer_cache -p 6379:6379 redis:5.0.3`
  
        `docker run -it --name product_cache -p 6380:6380 redis:5.0.3`

# Arquitetura da solução 

![DesafioArquitetura](https://user-images.githubusercontent.com/56052145/110580285-1468b680-8147-11eb-8839-e73186a7c7b1.png)
