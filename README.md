# 
# Spring Boot Microservice Demo

A demo to create an Employee management system using spring boot Microservice architecture. 

## Features

- Endpoint to save an employee with fields - employee id, firstname, lastname, emailaddress and department.
- Endpoint to find an employee by Id.
- Endpoint to find employees with maximum salary.
- Endpoint to find employees with minimum salary.


## Tech Stack

Java 17, Spring Boot 3.0.3, Maven


## API Reference

#### Save an employee

```http
  POST /api/employee
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get employee

```http
  GET /api/employee/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of employee to fetch |

#### Get employee by name

```http
  GET /api/employee/findemployeeByName
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. name of employee to fetch |

#### Get employee by minimum salary

```http
  GET /api/employee/findemployeeByMinSalary
```

#### Get employees with maximum salary

```http
  GET /api/employee/findemployeeByMaxSalary
```
## Author

- [@pujashah25](https://github.com/pujashah25)

