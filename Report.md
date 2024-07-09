# Reports on the project YouTube
![Static Badge](https://img.shields.io/badge/SBU_University-b?logo=bookstack&logoColor=red&labelColor=black&color=green)
![powered by](https://img.shields.io/badge/Powered_By-JAVA-blue)

## Table of contents 🧾

- [Introduction 💁](https://github.com/Amin-Gh-05/YouTube/new/Reports.md#first-of-all)
- [Objectives 🎯](https://github.com/Amin-Gh-05/YouTube/new/Reports.md#objectives-)
- [UML Designs 🧭](https://github.com/Amin-Gh-05/YouTube/edit/Reports/Report.md#uml-design)
- [The Process ♟](https://github.com/Amin-Gh-05/YouTube/edit/Reports/Report.md#the-process)
  - [Phase 1](https://github.com/Amin-Gh-05/YouTube/edit/Reports/Report.md#phase-1)
  - [Phase 2](https://github.com/Amin-Gh-05/YouTube/edit/Reports/Report.md#phase-2)
  - [Phase 3](https://github.com/Amin-Gh-05/YouTube/edit/Reports/Report.md#phase-3)
- [Ideas And Chalenges 🧠]
- [Creators 👩🏽‍💻](
- [Special Thanks to...🙏]
- [Resources 📚](https://github.com/Amin-Gh-05/YouTube/new/Reports.md#resources)

## First of all💁‍♂️
This app is our final project for [Advanced Programming](https://github.com/Advanced-Programming-1402) course at Shahid Beheshti University. The program is based on Java and we also used some other languages such as CSS and Html.
<br>The design pattern used for this project is MVC. For further information on this design pattern check the ***The Process*** section.

## Objectives🎯

- Object Oriented Programming(OOP) Concepts
- Database Design
- Multithreading Concepts
- Socket Programming
- Designing Graphical User Interface With JavaFX
- Data Encryption
- Design Patterns

## UML Designs🧭

![umls]()

## The Process♟
### Phase 1
At the beggining of the projects, we had several meetings discussing different subjects such as:
1. Which design pattern should we use?
<br>There was a wide range of patterns we could use, patterns like Bridge, Abstract factory, Composite and so on. At last we picked MVC design pattern(short for model-view-controler).
<br>MVC separates the different aspects of an application (data, UI, and logic), making the code easier to understand, maintain, and modify.
Since the components are independent, changes to one component do not affect the others, allowing for easier updates and modifications and since the parts are seperated, multiple developers can work on different components simultaneously, which makes this pattern our finest option to work on as a group.
2. How to create our API?
<br>We used gson library to send request and recieve response which let us exchange different objects easier and faster than normal string.
4. How should we manage the database?
<br>Our database has been rewritten almost three times, adding new objects and attributes in each time. This database uses CRUD designing, short for create-read-update-delete.
<br>We also used DTOs which is short for "Data Transfer Object" to make data exchange faster and more efficient.
6. Basic map of the program and division of labour
<br>On this phase, the program was divided into Backend and Frontend. But in the process, we had a lot of time working on the same file or debuging two parts which were connected in functionality.

### Phase 2
On this phase, we started designing all FXML views in the project, filling them with buttons and setting alerts and notifications. 
> mediaplayer
> comments

### Phase 3
About half of the program is being executed on our main panel. On this panel, we load other panels such as video view, channels, profile etc.
> how to download video


### Ideas And Chalenges🧠
In this program, we normally don't change scenes, most of panels are being loaded on the main panel and studio panel using FXMLLoader and passing controllers. 
> server log and history
> client and server


## Program Made By👩🏽‍💻
- Amin Ghoorchian
- AmirAli Araghi
- Sepide Hoseini

## Special thanks to🙏
- Teacher: Dr.SaeedReza Kheradpishe
- Head TA: Mahan Madani
- Mentor: Shayan Shahrabi

## Resources📚

- https://refactoring.guru/design-patterns
- https://www.geeksforgeeks.org
- https://sqlbolt.com/



