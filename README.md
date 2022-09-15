# purchase-module

This project is an attempt to implement a simple application with REST API inspired by clean architecture by Uncle Bob.

The basic use case is to buy items using credit card. If card limit is exceeded, then the purchase is rejected. Each call to an API is treated as a single purchase attempt. One purchase request can contain many items and must contain card number. Authorization of a buyer is ommited in this example.

The application structure has several layers:
1. Domain - contains business logic separated intentionally from frameworks like Spring or Hibernate. Exception would be for vavr, which is treated as java collections replacement (and more than that).
2. Application - contains actions, which serve as an orchestrators for different layers
3. API - contains DTOs and RestControllers
4. Repositories - contains implementations of *Repository interfaces from domain layer and integrations with Hibernate
5. Configuration - contains Spring boot configuration classes

Design decisions:
1. The whole module is intented to be small, yet structured accordingly to clean architecture rules:
  - domain contains business objects which depend only on different business objects
  - JPA/Hibernate is a plugin to domain and could be replaced by something else without affecting business rules
  - Spring Boot is also a plugin
2. Domain is slightly inspired by Domain Driven Design, although for such simple application seems to be an overkill. Nevertheless, if we would face real business requirements, then this approach is preferred when developing application further.
3. Vavr is used instead of Java collections and streams because the idea was to code business rules more functionally.
4. H2 in-memory database is used just for POC.
5. Hibernate and Spring Boot are used because they play together nicely and allow to develop relatively quickly. It could be even faster if we would put JPA entities in the middle of the domain, but according to clean architecture, it's discouraged.

Problems:
1. Translating between JPA entities and business objects is a pain. For example, in ChargeRepository there was a need for CreditCardJPA loading for the second time, because the Hibernate session was lost.
2. Vavr validation is somewhat hard. Validating vavr collections with Hibernate Validator require additional dependency and I didn't have time to check it.
3. Domain objects access is mainly public, which is not so nice. I was thinking of modularizing this app more, but again - I didn't have time to do that.
4. JUnit5 is used, although I think that Spock would be better. Enough to say that I needed to juggle between 4 versions of JUnit5 to make it work.
6. Concurrent access is not tested.
7. Authorization is not covered.

Ideas for further improvement:
1. Find a way to make Hibernate entities work better with domain objects
2. Use Java modules to clearly demarcate layers.
3. Use Spock instead of JUnit5.
4. Unit test MakePurchaseAction.
5. Implement interaction with authorization module.
6. Develop more validations.
7. Experiment with different data sources, not only relational.
