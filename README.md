# MoneyMotion

## Preview

>MoneyMotion is an application that will keep track of the **location** of the user's money by documenting money *lent* and *owed* to others. Its ease of accessibility and usability will allow anybody
>that desires to improve their financial organization to use it to its *full potential*. I am interested in creating this project as it will improve my own financial decisions, organization, as 
>well as provide others with a reliable form of keeping track of where their money has gone and where it is going. Personal finance is only becoming more of a necessity in our modern world, and
>I'm planning for this application to not only raise more awareness towards it, but also to provide a potential solution.
>
## User Stories
>
> - As a user, I want to be able to create new Contacts and provide additional details about them
>> **Details:** Name, a brief description, transaction amount, and date
> - As a user, I want to be able to add Contacts to a specific Category (debts, loans, neutral)
> - As a user, I want to be able to update a Contact's transaction details (how much debt has been paid off or how much of the loan has been paid back)
> - As a user, I want to be able to select a "Category" and see which "Contacts" belong inside it
> - As a user, I want to be able to search for a Contact by their *name* in a Category
> - As a user, I want to be able to select a "Contact" and see the details of that Contact
> - As a user, I want to be able to manually save all the Contacts and their details in each Category at anytime to file
> - As a user, when exiting the program, I want to be able to choose to save all the Contacts and their details in each Category to file
> - As a user, I want to be able to load all of the Contacts that were previously placed in each Category from file automatically when the program starts

## Instructions for Grader

> - You can generate the first required event by: Clicking on "Start" -> "Create a New Contact" -> Filling in the properties of the Contact -> "Create Contact" (button event)
> - You can generate the second required event in 2 ways:
>> 1. "Start" -> "Categories" -> "Debts" or "Loans" or "Neutral" -> Searching for a Contact by name in the Search Bar located at the top (keyboard event)
>> 2. "Start" -> "Categories" -> "Debts" or "Loans" or "Neutral" -> Click on a Contact -> "View Details" (button event)
> - You can locate my visual component by: My main logo found in the **main menu** and my logo at the top left corner of several scenes
> - You can locate my audio component by: "Start" -> "Create a New Contact" -> Filling in the properties of the Contact -> Turn up your sound -> "Create Contact" -> "Ding" Sound indicating Contact has been successfully created
> - You can save the state of my application by: "Quit" -> "Yes" OR "Start" -> "Save Contacts"
> - The application *automatically* loads the saved state from previous sessions

## Phase 4: Task 2
> In my program, I have chosen to implement option 2: incorporating a **type hierarchy** into my code (other than the Saveable interface). My type hierarchy consists of the *Debts*, *Loans*, and *Neutral* Classes which all 
> extend the *Category* Abstract Class. Furthermore, *Category* contains the declaration of the *totalFinances* Abstract Method, with each of *Category*'*s* Subclasses containing their
> own unique implementation for the *totalFinances* Method.

## Phase 4: Task 3
> The first problem that I decided to address within my code was the low cohesion that existed within my *SceneManager* Class. When I first created the Class, its responsibility was intended
> to be for general operations that were used to adjust the state and properties of the scenes within my program. However, as I introduced more operations into my code, I mistakenly clumped them
> into the *SceneManager* Class, causing its responsibilities to broaden and extend beyond what was originally intended. To fix this issue, I created two new classes: 
> *ButtonManager* and *TableManager*. With these two new classes, with the first handling buttons and button effects and the second handling tables and table properties, I was able to refactor some of the
> Methods within the *SceneManager* class and move them into the appropriate class out of the two. More specifically, I moved the **createSearchBar** Method into the *TableManager* Class and 
> the **hoverEffect** Method into the *ButtonManager* Class. Furthermore, to fix the compilation errors that occurred, I created new association relationships between all of the Controller classes and the new
> *ButtonManager* and *TableManager* Classes by declaring and instantiating a field of their types and invoking the corresponding Methods needed within the particular scene.

> The second problem that I addressed was the high coupling between the *ControllerDebts*, *ControllerLoans*, and *ControllerNeutral* Classes. I noticed that, within the **initialize** Method of each of these classes,
> there existed highly problematic coupling in the form of duplicated implementation. As I learned through the videos and lectures, having the same implementation that must be maintained between several different Methods
> is very problematic as any changes made to one would have to also be made to all of the rest. Furthermore, this issue is not something that the compiler can detect for us, and so it becomes much more difficult 
> to identify when the implementation become out of sync. Interestingly enough, I actually experienced the consequences of this problem first hand when I noticed that the implementation within the **initialize** Method in the
> *ControllerNeutral* Class was slightly different from the other two Controller classes, causing the UI to appear slightly different than what I wanted. To fix this issue, I refactored the implementation common to the **initialize**
> Methods of all 3 of the Controller Classes by abstracting it into a brand new Method: **initializeTable**. I placed this new Method into the *TableManager* Class to avoid any issues involving cohesion and, in order to make sure the
> I did not change the functionality of the program, I invoked **initializeTable** in the **initialize** Methods of each of the 3 Controller Methods along with passing in their corresponding arguments.