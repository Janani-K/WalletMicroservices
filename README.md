# WalletMicroservices
A monetary account holds the current balance for a player. The balance can be modified by registering transactions on the account, either debit transactions (removing funds) or credit transactions (adding funds). Create a REST API and an implementation that fulfils the requirements detailed below and honors the constraints.

Assumptions:
 - Caller will not be giving the transaction id, instead it is generated at the run-time. This can also be implemented in a way transaction id is accepted from user and checked if the transaction id is already present and perform action accordingly
 - Additional API to add player is developed which can be used if required

Note: 
- All invalid cases have been handled with exceptions
- Sample request/response for all APIs are given as Postman Collection at resources->collection
- H2 console URL : http://localhost:8080/h2-ui/
- Tables: Players (player_id, balance) & Transaction(transaction_id, player_id, transaction_type, transaction_amount, created_at)
