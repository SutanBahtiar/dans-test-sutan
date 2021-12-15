<!-- PROJECT SHIELDS -->
[![LinkedIn][linkedin-shield]][linkedin-url]


## About The Project
Secure Api With JWT

### Stack
1. Java 1.8
2. Spring Boot
3. Maven
4. [Apache Http Client](https://hc.apache.org/httpcomponents-client-5.1.x/)


## How to start
```sh
make start or mvn spring-boot:run 
```

Get Token
```sh
 curl --location --request POST 'http://localhost:8082/authenticate' --header 'Content-Type: application/json' --data-raw '{"userName":"username","password":"password"}'
```

Get Positions
```sh
 curl --location --request GET 'http://localhost:8082/positions' --header 'Authorization: Bearer {token}'
```

Get Positions ById
```sh
 curl --location --request GET 'http://localhost:8082/positions/{ID}' --header 'Authorization: Bearer {token}'
```

## License
Distributed under the Apache License 2.0. See [LICENSE](LICENSE#section) for more information.

## Contact
Sutan Bahtiar - [LinkedIn](https://www.linkedin.com/in/sutan-bahtiar-97026735) - sutan.bahtiar@gmail.com

<!-- MARKDOWN LINKS & IMAGES -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/sutan-bahtiar-97026735
