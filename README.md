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

# Responsibilities: 
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
