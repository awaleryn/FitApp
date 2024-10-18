# FitApp

- ✅ Java 17

- ✅ Spring Boot 3.3.2

- ✅ Spring Security

- ✅ Spring Data JPA

- ✅ Spring Email

- ✅ MapStruct

- ✅ Maven

- ✅ Cucumber

- ✅ Junit

- ✅ Mockito

- ✅ OpenAI API from [com.theokanning.openai-gpt3-java](https://github.com/TheoKanning/openai-java?tab=rea)

- ✅ MySql

- ✅ Postman


# About project

FitApp is a comprehensive fitness tracking application designed to help users manage their workout routines, dietary plans, and physical activities. The application provides a user-friendly interface for monitoring fitness progress and customizing goals, all while ensuring data security and integrity.

### Key Features:
- Workout and Diet Tracking: Users can log workouts and track their dietary habits to meet fitness goals.
  
- Data Persistence: Built using Spring Boot and Spring Data JPA for efficient database management and seamless integration with relational databases.
  
- Security: Uses Spring Security with JWT (JSON Web Tokens) for secure authentication and authorization, ensuring only authorized users can access specific resources.
  
- Modular Architecture: The application follows a layered architecture, including controllers, services, and repositories, ensuring clean code and ease of maintenance.

# How it works

## Responses from postman
#### Registering users (available for everone):
![image](https://github.com/user-attachments/assets/a5d11508-bb09-4923-9005-31950e335d0d)

#### User authenticates to generate JWT tokens used in authorization for other endpoints:
![image](https://github.com/user-attachments/assets/d0f47233-8b73-4331-b955-77385c95d562)

#### Refresh token before expiration
![image](https://github.com/user-attachments/assets/1f681ec8-a670-4650-98ad-9aba264b68af)

#### User logout
![image](https://github.com/user-attachments/assets/9d86e97d-5086-470c-ac6e-5ae3daa4a644)

#### Forgot password? Send new to email
![image](https://github.com/user-attachments/assets/e492ee36-1b92-42d6-b7ff-1337ef39fb69)

#### Response:
![image](https://github.com/user-attachments/assets/5545d16f-36ee-4e30-a3b4-c1842ff3c36e)

#### Login
![image](https://github.com/user-attachments/assets/8cd27a5e-1fa0-4565-b40d-66ab135a8309)

#### Change password
![image](https://github.com/user-attachments/assets/96d2287a-2754-4447-9476-acced1130a1b)


## OpenAI requests

#### User gets breakfast idea from OpenAI based on provided products
![image](https://github.com/user-attachments/assets/d6cf64ee-a455-4533-aa1d-7bdd36735f7b)

#### User gets dinner idea from OpenAI based on provided products
![image](https://github.com/user-attachments/assets/29ae11c2-9524-4edf-9ca7-9a0916cec1c5)

#### User gets lunch idea from OpenAI based on provided products
![image](https://github.com/user-attachments/assets/0c266b51-e1d6-4ecb-a5ea-0966c074afe8)

#### Fit products worth buying
![image](https://github.com/user-attachments/assets/cd7d16d8-f1ac-4a5d-b764-f696373ebc2c)

## Products, BMI and daily intake

#### User adds product with calories and macronutrients:
![image](https://github.com/user-attachments/assets/38eb296f-a3dd-4b12-9ce4-70d45b0fca6e)

#### User adds list of products with calories and macronutrients:
![image](https://github.com/user-attachments/assets/272db01a-8a50-4f12-bdfd-4d4e992e459b)
#### Response:
![image](https://github.com/user-attachments/assets/e8f1dd66-d944-4a76-942a-ef52c9957632)

#### User gets list of all products:
![image](https://github.com/user-attachments/assets/029b1cac-98fb-4930-8415-7ea93f1270f1)

#### User gets list of products of selected type:
![image](https://github.com/user-attachments/assets/f4defc1d-da76-46fb-b39c-a8e1555c4247)

#### User gets BMI and nutritions for daily intake:
![image](https://github.com/user-attachments/assets/df0b1543-767c-40c4-88b9-239097fb44da)

#### User adds product to his daily intake:
![image](https://github.com/user-attachments/assets/07f00752-452e-44ec-acb0-31c58bbd3f10)

#### User gets his whole daily intake history
![image](https://github.com/user-attachments/assets/64cc9579-d043-4e0a-9c53-1c34a73cab04)

#### User gets his daily intake history based on date
![image](https://github.com/user-attachments/assets/17f72a4b-f195-4bd7-b3a6-6179fdb9a763)

## Exception diagram

![image](https://github.com/user-attachments/assets/c0e56f50-6cb6-4b93-9144-f149bad9e597)

## Entity diagram

![image](https://github.com/user-attachments/assets/b1c6855e-1936-4a81-ad97-cf94bdbba948)

## Test package diagram

![image](https://github.com/user-attachments/assets/19d1ffa8-d172-4b5e-875c-b090f9bd8dec)


