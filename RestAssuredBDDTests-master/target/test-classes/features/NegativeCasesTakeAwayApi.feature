Feature: Negative Cases for Takeaway Task API's

  @Smoke @Negative @InvalidData
  Scenario Outline: Check AddTaskAPI add task empty title
    Given Get a valid token
    And a invalid AddTaskAPI payload with data ""
    When AddTaskAPI request is sent as Post HTTP request with invalid data
    Then fail api response is sent back with status code 422
    And response message value in response is "The given data was invalid."

  @Smoke @Negative @InvalidData
  Scenario Outline: Check AddTaskAPI add task null title
    Given Get a valid token
    And a invalid AddTaskAPI payload with data null
    When AddTaskAPI request is sent as Post HTTP request with invalid data
    Then fail api response is sent back with status code 422
    And response message value in response is "The given data was invalid."

  @Smoke @Negative @InvalidData
  Scenario Outline: Check AddTaskAPI add task title with long characters
    Given Get a valid token
    And a invalid AddTaskAPI payload with data "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
    When AddTaskAPI request is sent as Post HTTP request with invalid data
    Then fail api response is sent back with status code 422
    And response message value in response is "The given data was invalid."
    And errors.title value in response body is "The title may not be greater than 255 characters."

  @Smoke @Negative @InvalidData
  Scenario Outline: Check get task with invalid id
    Given Get a valid token
    And a invalid GetTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request with invalid data
    Then fail api response is sent back with status code 404

  @Smoke @Negative @InvalidData
  Scenario Outline: Check put task with invalid id
    Given Get a valid token
    And a invalid PutTaskAPI payload with data "Update Task"
    When PutTaskAPI request is sent as Put HTTP request with invalid data
    Then fail api response is sent back with status code 404

  @Smoke @Negative @InvalidData
  Scenario Outline: Check put task with empty title
    Given Get a valid token
    And a invalid PutTaskAPI payload with data ""
    When PutTaskAPI request is sent as Put HTTP request with invalid data
    Then fail api response is sent back with status code 422
    And response message value in response is "The given data was invalid."

  @Smoke @Negative @InvalidData
  Scenario Outline: Check put task with null title
    Given Get a valid token
    And a invalid PutTaskAPI payload with data null
    When PutTaskAPI request is sent as Put HTTP request with invalid data
    Then fail api response is sent back with status code 422 
    And response message value in response is "The given data was invalid."

  @Smoke @Negative @InvalidData
  Scenario Outline: Validate delete task with invalid id
    Given Get a valid token
    And a invalid DeleteTaskAPI request
    When DeleteTaskAPI request is sent as Delete HTTP request with invalid data
    Then fail api response is sent back with status code 404

  @Smoke @Negative @NoAuth
  Scenario Outline: Check AddTaskAPI without tokens
    Given Get a invalid token
    And a valid AddTaskAPI payload with data "A newly created task"
    When AddTaskAPI request is sent as Post HTTP request with invalid data
    Then fail api response is sent back with status code 401
    And response message value in response is "Unauthenticated."

  @Smoke @Negative @NoAuth
  Scenario Outline: Check getTaskAPI  without tokens
    Given Get a invalid token
    And a valid GetTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request with invalid token
    Then fail api response is sent back with status code 401
    And response message value in response is "Unauthenticated."

  @Smoke @Negative @NoAuth
  Scenario Outline: Check getAllTaskAPI  without tokens
    Given Get a invalid token
    And a valid getAllTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request with invalid token
    Then fail api response is sent back with status code 401
    And response message value in response is "Unauthenticated."

  @Smoke @Negative @NoAuth
  Scenario Outline: Check PutTaskAPI  without tokens
    Given Get a invalid token
    And a valid PutTaskAPI payload with data "A newly created task"
    When PutTaskAPI request is sent as Put HTTP request with invalid token
    Then fail api response is sent back with status code 401
    And response message value in response is "Unauthenticated."

  @Smoke @Negative @NoAuth
  Scenario Outline: Check deleteTaskAPI  without tokens
    Given Get a invalid token
    And a valid deleteTaskAPI request
    When deleteTaskAPI request is sent as Delete HTTP request with invalid token
    Then fail api response is sent back with status code 401
    And response message value in response is "Unauthenticated."

  @Smoke @Negative @InvalidEndPoint
  Scenario Outline: Check AddTaskAPI with invalid endpoint
    Given Get a valid token
    And a valid AddTaskAPI payload with data "A newly created task"
    When AddTaskAPI request is sent as Post HTTP request with invalid endpoint
    Then fail api response is sent back with status code 404
    And status value in response body is "404 Not Found"

  @Smoke @Negative @InvalidEndPoint
  Scenario Outline: Check getTaskAPI invalid endpoint
    Given Get a valid token
    And a valid GetTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request with invalid endpoint
    Then fail api response is sent back with status code 404
    And status value in response body is "404 Not Found"

  @Smoke @Negative @InvalidEndPoint
  Scenario Outline: Check getAllTaskAPI invalid endpoint
    Given Get a valid token
    And a valid getAllTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request with invalid endpoint
    Then fail api response is sent back with status code 404
    And status value in response body is "404 Not Found"

  @Smoke @Negative @InvalidEndPoint
  Scenario Outline: Check PutTaskAPI invalid endpoint
    Given Get a valid token
    And a valid PutTaskAPI payload with data "A newly created task"
    When PutTaskAPI request is sent as Put HTTP request with invalid endpoint
    Then fail api response is sent back with status code 404
    And status value in response body is "404 Not Found"

  @Smoke @Negative @InvalidEndPoint
  Scenario Outline: Check deleteTaskAPI invalid endpoint
    Given Get a valid token
    And a valid deleteTaskAPI request
    When deleteTaskAPI request is sent as Delete HTTP request with invalid endpoint
    Then fail api response is sent back with status code 404
    And status value in response body is "404 Not Found"
