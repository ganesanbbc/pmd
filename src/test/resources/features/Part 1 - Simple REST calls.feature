Feature: Products inventory with simple REST calls

  Scenario: Invoke the get product api
    When i call get "/getProducts"
    Then the response status should be 200
