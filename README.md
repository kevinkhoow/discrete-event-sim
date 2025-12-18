# Discrete Event Simulator

## Overview

This project serves to demonstrate **Object-oriented Programming** (OOP) and **Functional Programming** (FP) principles, by modelling a shop with multiple servers or checkout counters. It tracks customers from their arrival to their departure from the shop, where they may be served, wait in a queue or leave due to capacity constraints. At the end of the simulation, it computes key performance metrics.

This project is developed as part of the NUS course CS2030 Programming Methodology II.

## Project Structure

```
discrete-event-sim
├── README.md
├── Maybe.java
├── InfList.java
├── PQ.java
├── Pair.java
├── Customer.java
├── Server.java
├── Shop.java
├── State.java
├── Event.java
├── ArriveEvent.java
├── LeaveEvent.java
├── WaitEvent.java
├── WaitingEvent.java
├── ServeEvent.java
├── DoneEvent.java
├── Simulator.java
└── Main.java
```

## Input

To model the arrival of `c` customers to a shop with `s` servers, the input consists of the number of servers `s`, the maximum capacity of each queue `qmax` and the number of customers `c`, separated by a whitespace.

This is followed by `c` rows, consisting of each customer's ID and arrival time, separated by a whitespace, in the order in which they arrive. Sample inputs are provided below.

```
1 1 6
1 0.500 
2 0.600 
3 0.700 
4 1.500 
5 1.600 
6 1.700 
```
```
2 1 6
1 0.500 
2 0.600 
3 0.700 
4 1.500 
5 1.600 
6 1.700 
```
```
2 2 6
1 0.500 
2 0.600 
3 0.700 
4 1.500 
5 1.600 
6 1.700 
```

The program assumes a constant service time of 1.0. Service time is provided by a Supplier<Double> in line 11 of `Main.java`, which may be edited accordingly. For instance, the line could be edited to: 
```
Supplier<Double> serviceTime = () -> sc.nextDouble();
```
In this case, additional rows should be provided in the input, with each row consisting of a service time, in the order in which service begins. The number of additional rows provided should correspond to the number of customers who are served. A sample input is provided below.
```
1 1 6
1 0.500 
2 0.600 
3 0.700 
4 1.500 
5 1.600 
6 1.700
1.1
0.1
2.0
3.0
```

## Output

Output consists of descriptions of each event in the simulation. Each row consists of the time the event occurred, followed by a written description of that event. The last row consists of the performance metrics computed by the simulation, i.e. average wait time for customers who have been served, number of customers served and number of customers who left without being served, in that order.

## Setup

#### Clone the Repository
```
git clone https://github.com/kevinkhoow/discrete-event-sim.git
cd discrete-event-sim
```

#### Create Input File
```
vim 1.in
```
To save and exit a vim file, ensure you are in Normal mode by pressing `Esc`, then type `:wq` and press `Enter`.

#### Compile and Execute Program
```
javac *.java
cat 1.in | java Main
```
