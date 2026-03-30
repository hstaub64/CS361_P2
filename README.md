# CS361_P2 - Nondeterministic Finite Automata
* Authors: Dean Cunningham, Haylee Staub
* Class: CS 361 Section 001
* Semester: Spring 2026

## Overview
This project is an implementation of a Nondeterministic Finite automata in Java,
where NFAs can be instantiated as a class and given a defined 5-tuple of language,
states, start state, final states, and transitions. The NFA can read strings and
determine if they would be accepted, or the max copies the machine will produce at
once while operating on a string.

## Sources
https://www.geeksforgeeks.org/java/hashmap-computeifabsent-method-in-java-with-examples/
Used to understand the "computeifabsent" command on hash lists, which I used to procedurally
add elements to NFAState's transition table as needed.