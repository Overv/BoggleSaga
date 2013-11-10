Boggle Saga
===========

This is a Java implementation of the classic 4x4 Boggle game created for the
Software Engineering Methods subject at the Delft University of Technology.

Developers
----------

* **Alexander Overvoorde** &mdash; 4153235 (aovervoorde)
* **René van den Berg** &mdash; 1528939 (rjwvandenberg)
* **Robert Carosi** &mdash; 4242130 (rcarosi)

Organization
------------

The project will be developed with IntelliJ IDEA, of which the project file is
included in this repository. Attention should be paid to **using design
patterns** learned in class *when appropriate* and **writing tests** for each
component.

The three components can mostly be developed separately, but since at some point
the view, controller and model need to be connected in some way, **commit early
and often** so other team members can start interfacing with your code.

The standard Java coding conventions should be used with `CamelCase` class names
and `camelCase` variable and member names and of course 4 spaces for tabs. This
is necessary to make merges work smoothly.

Release checklist
-----------------

* No unsolved compile or runtime errors
* Play tested
* Unit tested

Road map
--------

The first sprint will end on **Sunday, 20 October** and the following features
will be implemented by the specified members of the team.

### Alexander

**View** with:

* Board along with dragging over dice for word selection
* Text based timer
* Score counter

### René

**Controller**, which handles:

* Checking words
* Ending the game when the time runs out by opening the end game window

### Robert

**Model** with:

* Current state of the game (grid, score, words guessed, timer)
* Dictionary with allowed words
