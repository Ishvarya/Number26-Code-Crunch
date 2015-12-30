# Number26-Code-Crunch
Solution-
1.	Storage and retrieval done using JSON List
2.	Service: RESTful Web Service
3.	(Object: Transaction ) Attributes: {transaction_id, transaction_type, transaction_amount, parent_transaction_id}
4.	Transaction_id is unique
5.	If a transaction has multiple sub-transactions, the parent_transaction_id of the topmost parent transaction is assumed to be NULL. Transaction hierarchy should be maintained.
6.	Else if a transaction is a single transaction (no sub-transaction), the parent_transaction_id is NULL.
7.	The transaction_type of parent and sub-transactions can be different (Transaction type has no dependency on the hierarchy or any other attribute) 
8.	The two retrieval services are independent of each other.

Web pages-
1.	Retrieval based on transaction type (Input: transaction_type, Output: List of all transaction_ids)
2.	Retrieval based on parent_transaction_id (Input: transaction_id, Output: ∑transaction_amounts)

API Specification-

1.	Transaction Storage
PUT /transactionservice/transaction/$transaction_id
{ "amount":double,"type":string,"parent_id":long }
Where,
transaction_id is a long specifying a new transaction
amount is a double specifying the amount
type is a string specifying a type of the transaction.
parent_id is an optional long that may specify the parent transaction of this transaction.

2.	Retrieval based on transation_type

GET /transactionservice/transaction/$transaction_id
Returns,
{ "amount":double,"type":string,"parent_id":long }

GET /transactionservice/types/$type
Returns, 
[ long, long, .... ]
A json list of all transaction ids that share the same type $type.

3.	Retreival based on transaction_id

GET /transactionservice/sum/$transaction_id
Returns,
{ "sum", double }
A sum of all transactions that are transitively linked by their parent_id to $transaction_id.

Implementation - Implemented as a Maven Project (Web Application, Java Version 7) om Netbeans IDE and tested using Apache Tomcat 8.0

