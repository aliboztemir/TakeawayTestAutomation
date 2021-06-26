Feature: Validating Takeaway Task API's

  @Smoke @Stress
  Scenario Outline: Get a valid token
    Given Post a valid token with LoginAPI with data "test@test.com" "4nak1n"
    When LoginAPI request is sent as Post HTTP request
    Then success response is sent back with status code 201

  @Smoke @Stress
  Scenario Outline: Validate AddTaskAPI add task
    Given a valid AddTaskAPI payload with data "A newly created task"
    When AddTaskAPI request is sent as Post HTTP request
    Then success response is sent back with status code 201
    And data.title value in response is "A newly created task"
    And data.author.name value in response is "test"
    And data.author.email value in response is "test@test.com"
    And length of data.id value in response must be greater than zero
    And response time must be less than 1000 ms

  @Smoke @Stress
  Scenario Outline: Validate get task with id
    Given a valid GetTaskAPI request
    When GetTaskAPI request is sent as Get HTTP request
    Then success response is sent back with status code 200
    And data.title value in response is "A newly created task"
    And data.author.name value in response is "test"
    And data.author.email value in response is "test@test.com"
    And length of data.id value in response must be greater than zero
    And response time must be less than 1000 ms

  @Smoke @Stress
  Scenario Outline: Validate get all tasks
    Given a valid GetAllTaskAPI request
    When GetAllTaskAPI request is sent as Get HTTP request
    Then success response is sent back with status code 200

  @Smoke @Stress
  Scenario Outline: Validate put task with id
    Given a valid PutTaskAPI payload with data "Update Task"
    When PutTaskAPI request is sent as Put HTTP request
    Then success response is sent back with status code 200
    And data.title value in response is "Update Task"
    And data.author.name value in response is "test"
    And data.author.email value in response is "test@test.com"
    And length of data.id value in response must be greater than zero
    And response time must be less than 1000 ms

  @Smoke @Stress
  Scenario Outline: Validate delete task with id
    Given a valid DeleteTaskAPI request
    When DeleteTaskAPI request is sent as Delete HTTP request
    Then success response is sent back with status code 204