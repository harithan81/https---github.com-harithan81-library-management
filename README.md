Master branch build status: [![Master Branch Build Status](https://travis-ci.org/harithan81/library-management.svg?branch=master)](https://travis-ci.org/harithan81/library-management)
[![Coverage Status](https://coveralls.io/repos/harithan81/library-management/badge.svg)](https://coveralls.io/r/harithan81/library-management)
# library-management

Library Management project is developed to provide features such as
- [Create Book](https://github.com/harithan81/library-management/blob/master/Docs/CreateBook.docx)
- [Catalogue Search](https://github.com/harithan81/library-management/blob/master/Docs/CatalogueSearch.docx)
- [Register User](https://github.com/harithan81/library-management/blob/master/Docs/RegisterUser.docx)
- [Borrow Book](https://github.com/harithan81/library-management/blob/master/Docs/Borrow.docx)
- [Renew Book](https://github.com/harithan81/library-management/blob/master/Docs/Renew.docx)
- [Return Book](https://github.com/harithan81/library-management/blob/master/Docs/ReturnBook.docx)

# How to use this application (Rest API)
- Create Book

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|   /book  |  POST  |    asd   |    Creates new book and returns created book   |
|  /book/bookId  |  GET |    |   search the book with given bookId and returns the book |
|    1  |    1 |     1   |     1  |

- Catologue Search

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|  /book/bookId  |  GET |    |   search the book with given bookId and returns the book |
|    1  |    1 |     1   |     1  |

- Register User

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|   /user  |  POST  |    asd   |    Creates new user and returns created user   |
|  /book/userId  |  GET |    |   search the user with given userId and returns the user |

- Borrow Book

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|   /book/bookId/borrow  |  POST  |    asd   |    Creates new book and returns created book   |



- Renew Book

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|   /userSctivity/userActivityId/renew  |  POST  |    asd   |    Renew the book with corresponding given user activity and update the Cheked out date return date  and returns the renewed book   |
|  /book/bookId  |  GET |    |   search the book with given bookId and returns the book |







|URI|Http|Body|Function|
|--|--|--|--|
|/book |POST|asd|aasd|

# Technology Stack: 


- Application is developed using Java.
- Rest Services developed using Spring web provide ability to perform CRUD(Create,Update and Delete) operations on       entity such as Book,User etc.
- Used frameworks such as Spring,Hibernate and Spring Data.
- Used JPA specification with hibernate as provider.
- Database data model is designed using Oracle Data Modeler.
- Test cases are developed using JUnit,DBUnit,Mock MVC for performing unit and integartion testing.
- Travis is used for Continous Integration


TODO

- Refactor replication code such that it can be reused.
- Make sure all use cases are working as expected such as checked out date in borrow use case.
- Replicate required properties of objects returned.
- Create informative mark down file
