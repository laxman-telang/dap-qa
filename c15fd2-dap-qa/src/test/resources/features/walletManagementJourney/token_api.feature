Feature: Tokenization API

  @api @token
  Scenario: Mint a new token
    Given the token mint payload with request ID "REQ001", issuer party ID "PARTY001", amount "1000", and asset type "GOLD"
    When the user sends a POST request to "/tokens/mint"
    Then the response status code should be 201
    And the response should contain "transaction_id"

  @api @token
  Scenario: Transfer a token
    Given the token transfer payload with request ID "REQ002", issuer party ID "PARTY001", target party ID "PARTY002", token ID "TOKEN123", and amount "500"
    When the user sends a POST request to "/tokens/transfer"
    Then the response status code should be 200
    And the response should contain "transaction_id"
