[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)

# Pitch
    You ever think "wow 🤔. I wish I had a online bullet journal 🥺". Well. Now you can. :D You might be thinking "woah, 
    that's crazy 🫨! how does this work?" Well. I'm here to tell you 🙀. We got your general display of a week with a
    whole buncha features 😻! You can enter new tasks and events, which are displayed so you can see all of your tasks
    and events for the week. Your tasks are also displayed on the side so you can see all of your tasks in one area.
    You can also save and open bullet journal files! Make sure you don't overbook yourself by inputting your desired 
    maximum number of tasks and events for the day! Keep that mental health up fam 😎

    Become an aesthetic bullet journal girlie 🍄 by choosing one of our present themes, or customizing your own theme!
    You can also put some aspirations 🤩, quotes 🗣️, and notes 📝 in our notes section. Keep track of how you're doing 
    on completing tasks by checking off tasks ✅ and watching the progress bar update 🤑. You can also getting a deeper 
    understanding 🧠 of your tasks and events by right clicking them to get a mini 🤏 view of them. And if you're 
    indecisive (just like me) or plans change, you can go back and edit your tasks and events by accessing that mini 
    view 😍.

    And you know we here at TOE <3 love to be extra so look around the program for flowers and our best friend Ducky 🪿 
    (both of which are clickable-- go do it :)). Your journal is EXTRA secure 😤🔒 with our username feature that 
    displays alongside the splash 💦 screen. Happy bujoing! 

    With love 🫶,
    the TOE <3 team 🦶🤭

# GUI Features Examples
    See GUIFeatures.drawio.png

# Implementation of SOLID Principles
    S: We used Single-Responsibility in the BujoJsonConverter class; specifically, the BujoJsonConverter class was 
       responsible for converting the data representing a week in the bullet journal into forms that could be
       either written to save or read to display from an opened file. 

    O: We used interfaces and abstract classes throughout our program, including the Controller, BujoInteractions, 
       BujoState, and View interfaces and the abstract Duty class. These interfaces and abstract classes allowed other
       classes to implement and extend their methods and fields without allowing them to modifying the methods
       themselves unless overriding in the subclass.

    L: We used Liskov's substitution in serializing and deserializing the JSON bujo file; in this process, we had to
       substitute in the superclass Duty for the subclasses Event and Task. This was done in order to abstract the
       process of storing both events and tasks without having to make the overall JSON structure too tightly coupled. 

    I: Interface segregation was used for the BujoInteractions and the BujoState interfaces. The BujoInteractions
       interface extended the BujoState interface. BujoState consisted of getters for the program while BujoInteractions
       updates the actual program.

    D: We used dependency injection in the creation of each new event and task from the controller and its event
       respective event handlers. Each "Duty" as we called them was created from the user inputting the necessary info
       into a dialog; these textfields were bound to blank instances of each new "Duty" and as their binded fields
       were updated in the dialog, they were also updated in the object itself. This proved very useful in that each
       new control could be updated without being too tightly coupled to the dialogs that helped update them. 

# How we could add another feature?
    We could implement Weekly Overview by having another Label somewhere on the journal. In order to show the statistics
    of total Events, total Tasks, and percent of tasks completed, we would add a List<Duty> field to the Week class, 
    which keeps track of every task and event added in the week. In the Week class, we would also make a method that 
    uses the aforementioned list to find the total number of tasks and events. 

    To get the percent of tasks completed, we would create a counter and call the getCompleted method on every task. For 
    every task that is complete, we would increase the counter by one. To get the percentage, we would then divid this 
    value by the total number of tasks as supplied by the method mentioned above. We would then take all of these values 
    and set the text of the labels to show these numbers.

# Image Attributions
    All images illustrated by Tracy Huang
    

