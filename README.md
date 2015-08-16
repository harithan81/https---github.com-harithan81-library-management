Master branch build status: [![Master Branch Build Status](https://travis-ci.org/harithan81/library-management.svg?branch=master)](https://travis-ci.org/harithan81/library-management)
# library-management

Library Management project is developed to provide features such as
- [Create Book](https://github.com/harithan81/library-management/blob/master/Docs/CreateBook.docx)
- [Catalogue Search](https://github.com/harithan81/library-management/blob/master/Docs/CatalogueSearch.docx)
- [Register User](https://github.com/harithan81/library-management/blob/master/Docs/RegisterUser.docx)
- [Borrow Book](https://github.com/harithan81/library-management/blob/master/Docs/Borrow.docx)
- [Renew Book](https://github.com/harithan81/library-management/blob/master/Docs/Renew.docx)
- [Return Book](https://github.com/harithan81/library-management/blob/master/Docs/ReturnBook.docx)

# How to use this application (Rest API)

| URI | HTTP Request Type | Body | What it does? |
|------:|:-----|---------|:------:|
|   /book  |  POST  |       |    Creates new book and returns created book   |
|  /book/{bookId}  |  GET |    |   Search the book with given bookId and returns the book |
|  /book  |  GET |    |   Search the book with given bookId and returns the book |
|   /user  |  POST  |       |    Creates new user and returns created user   |
|  /user/userId  |  GET |    |   Search the user with given userId and returns the user |
|   /book/bookId/borrow  |  POST  |     |    Borrow book and return borrowed  book   |
|   /userActivity/userActivityId/renew  |  POST  |      |    Renew the book return renewed book   |
|   /userActivity/userActivityId/return |  POST  |      |    Returns the book to catalogue  |


# Technology Stack: 


- Application is developed using Java.
- Rest Services developed using Spring web provide ability to perform CRUD(Create,Update and Delete) operations on       entity such as Book,User etc.
- Used frameworks such as Spring,Hibernate and Spring Data.
- Used JPA specification with hibernate as provider.
- Database data model is designed using Oracle Data Modeler.
- Test cases are developed using JUnit,DBUnit,Mock MVC for performing unit and integartion testing.
- Travis is used for Continous Integration

