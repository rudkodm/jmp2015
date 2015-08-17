# ACTORS AND ROLES

## Unauthorized users:

 - Access the portal.

 - News
 	- see newsline on the portal.
 
 - Books
 	- see the list and description of provided books.
 
 - Trainings
 	- see the list of trainings by categories.
 
 - Tests
 	- see the list of certification tests.
 

## Authorized users (extends Unauthorized users rights):

 - News
 	- read comments for news,
 	- leave comments for news.

 - Books
 	- select the book,
 	- enter payment data,
 	- order the book to be delivered by the post service.
 
 - Trainings
 	- can apply for participation in the training from the portal,
 	- can pay for the training there.

 - Tests
 	- can pass the online testing in several categories ,
 	- order paper cope of the certificate if it is available as a result of testing (paid additionally),
 	- order digital cope of the certificate if it is available as a result of testing (paid additionally).
 
 - Profiles
 	- edit personal info on the profile page,
 	- see the list of trainings the user attended before,
 	- see the list of trainings the user added to the group for the lessons in future,
 	- see on in the profile the list of passed tests,
 	- see on in the profile the list of available certificates.

 - Lessons
   	- able to see only the lessons, they participate in as attendees.


## Trainers (extends Unauthorized users rights):

- Lessons
	- can edit his lessons (excluding the list of attendees) if it is not active,
	- can create the draft of the lessons for the particular day and time in system,
	- able to view all lessons in all statuses.


## Admins (extends Trainers rights):


- News
	- can add news in newsline,
	- can edit news in newsline.

- Books
	- edit the list of categories for books,
	- add books to the bookstore page.

- Trainings
	- can add trainings,
	- can remove trainings,
	- can edit trainings,
	- edit the list of categories for trainings.

- Profiles
	- can edit user profiles,
	- can edit user roles.

- Lessons
	- edit data for the particular lessons of the training including the day and time, lector name and the group of attendees,
	- can change the parameters of the lessons in any moment,
	- can remove the lesson in any moment,
	- can create the list of attendees for the particular lessons.



# RULES 

## Lessons rules

The draft of the lessons for the particular day and time can be created in the system by both the admin and trainer. Lessons can include several days and time periods. If there is not finished lessons created for the training, training should be displayed in the system as actual.

The list of attendees for the particular lessons are created in the system by the admin only. When the list is filled and lessons are marked as active by admin the selected trainer (lector) and all attendees should receive e-mail notification. The training should become planned in this case. All attendees of the training should be registered in the system automatically.

If the first lesson of the training is already started but the last is not finished, the training should be in the “in progress” status. All trainers and admins should be able to view all lessons in all statuses.  Other authorized users should see only the lessons, they participate in as attendees.

The trainer can edit his lessons (excluding the list of attendees) if it is not active. Admin can change the parameters of the lessons or remove it in any moment


# TASKS

## Class (Domain model)

- Please, draw general class diagram for the system.
- Specifying attributes, connection types and multiplicities to depict the domain model.

 
## Package

- Please, draw the package diagram to group all service interfaces with can be used for the application by your point of view (or diagram for any other possible grouping: multi-layer application model; technology, should be used for data access for different classes etc).


## Component

- Please, draw the component diagram to depict the high-level architecture of the system (as you see it).
 
 
## Use Case

- Please draw use case diagram for all actors of the system. 
- Give attention to the use cases, general for several user roles and decomposition of User Cases.


## Activity

- Describe the process of creating and editing lesson in the system (starting from the creation of the training).
 

## State Machine

- Describe the flow of training states in the system.


## Sequence

- Draw the diagram, which describes the scenario of using the bookstore (selecting and making order and paying for the book) in the system (user is not registered in the system initially).

