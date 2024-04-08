# E-Commerce REST API

## Tech Stack Used

- Java
- JAX-RS (Java API for RESTful Web Services)
- Hibernate (JPA) for ORM (Object-Relational Mapping)
- MySQL database

## APIs Developed

**User Registration**
   - Endpoint: `/ecom/register`
   - Method: POST
   - Parameters: username, password
   - Example URL: `http://localhost:8080/ecom/register`
   - Example Request Body:
     ```json
     {
         "username": "john_doe",
         "password": "password123"
     }
     ```

**User Login**
   - Endpoint: `/ecom/login`
   - Method: POST
   - Parameters: username, password
   - Example URL: `http://localhost:8080/ecom/login`
   - Example Request Body:
     ```json
     {
         "username": "john_doe",
         "password": "password123"
     }
     ```
 
**Fetch Product Information**
   - Endpoint: `/ecom/products`
   - Method: GET
   - Example URL: `http://localhost:8080/ecom/products`


**Sort Product Based on Price**
   - Endpoint: `/ecom/sort`
   - Method: GET
   - Example URL: `http://localhost:8080/ecom/sort`
   
**Search Using Name and Category** 
   - Endpoint: `/ecom/search?name={productName}&category={Category}`
   - Method: GET
   - Example URL: `http://localhost:8080/ecom/search?name={productName}&category={Category}`
   - can be used with or without both query parameters

**Add Item to Cart**
   - Endpoint: `/ecom/cart`
   - Method: POST
   - Parameters: userId, productId, quantity
   - Example URL: `http://localhost:8080/ecom/cart`
   - Example Request Body:
     ```json
     {
         "userId": 1,
         "productId": 123,
         "quantity": 2
     }
     ```

**View Cart**
   - Endpoint: `/ecom/cart/{userId}`
   - Method: GET
   - Example URL: `http://localhost:8080/ecom/cart/{userId}`

**Remove Item from Cart**
   - Endpoint: `/ecom/cart/{cartItemId}/{userId}/{productId}`
   - Method: DELETE
   - Example URL:
`http://localhost:8080/ecom/cart/{cartItemId}/{userId}/{productId}`

