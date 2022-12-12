# Bank Account Management System

This project provides a system for managing bank accounts, including Checking, Savings, StudentChecking, and CreditCard accounts. The system supports 3 types of users: Admins, AccountHolders, and ThirdParty users. The project includes REST controllers for accessing and managing accounts.

## Account Types

- **StudentChecking**: Identical to Checking accounts, except that they do not have a monthly maintenance fee or minimum balance.
- **Checking**: A standard checking account with a balance, secret key, primary and optional secondary owner, minimum balance, penalty fee, monthly maintenance fee, creation date, and status.
- **Savings**: Similar to Checking accounts, except that they do not have a monthly maintenance fee and have an interest rate.
- **CreditCard**: A credit card account with a balance, primary and optional secondary owner, credit limit, interest rate, and penalty fee.

## User Types

- **Admins**: Admins have a name and can create new accounts.
- **AccountHolders**: AccountHolders have a name, date of birth, primary address, and optional mailing address. They can access their own accounts and transfer money between them.
- **ThirdParty**: ThirdParty users have a hashed key and a name. They must be added to the system by an Admin, and can receive and send money to other accounts.

## Interest and Fees

- Savings accounts have a default interest rate of 0.0025 and a minimum balance of 1000, but these can be customized at account creation.
- CreditCard accounts have a default credit limit of 100 and an interest rate of 0.2, but these can also be customized.
- All accounts have a default penalty fee of 40, which is applied if the account balance drops below the minimum balance.

## Accessing Accounts

- Admins can access the balance of any account and modify it using the provided REST controllers.
- AccountHolders can access the balance of their own accounts and transfer money between them using the provided REST controllers.
- ThirdParty users can receive and send money to other accounts using the provided REST controllers.

## Getting Started

1. Install the required dependencies.
2. Create a new Admin user to add ThirdParty users and create accounts.
3. Use the provided REST controllers to access and manage accounts.

