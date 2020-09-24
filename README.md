# Promotional Service


The Promotion Service is written Java 11. It models a simple checkout basket which allows the application of 
promotion rules to manipulate the total price paid by the customer. 


## Key Decisions
- The checkout interface uses BigDecimal instead of double for precision reasons.

- The product items have been hardcoded in a local repository (as oppose to an in-memory database).

- To simplify the solution no frameworks have been used. 3rd party libraries have been keep to a minimum.

- Comments have been added to the interfaces/classes to provide some context and reasoning.

- Each basket Item (represented by BasketItem.java) should know how to calculate it's own subtotal (as opposed to being an anemic object).
  The goal being to make the overall basket calculation easier to do and understand.
  
- The executor object does the heavy lifting - we can (in the future) have different executor which will process the rules in any order we see fit.

- The heavy use of interfaces should allow us to plugin/change functionality in the future without causing to much disruption.


## Running the tests

The application was built using maven, the tests are run using Junit5. 

The PromotionsCheckoutTest (com/noth/service/PromotionsCheckoutTest.java) are the highest level test, 
and test the scan() and total() interface methods.

The tests can be found here:
src/test/java/com/noth


To run the test using maven cli running the following command from the root directory:

> mvn clean test

