# Discrete Event Simulator

## Overivew

This project serves to demonstrate **Object-oriented Programming** (OOP) and **Functional Programming** (FP) principles, by modelling a shop with multiple servers or checkout counters. It tracks customers from their arrival to their departure from the shop, where they may be served, wait in a queue or leave due to capacity constraints. At the end of the simulation, it computes key performance metrics, i.e. average wait time for customers who have been served, number of customers served and number of customers who left without being served.

## Project Structure

## Input

To model the arrival of `c` customers to a shop with `s` servers, the input consists of the number of servers `s`, the maximum capacity of each queue `qmax` and the number of customers `c`, separated by a whitespace.

This is followed by `c` rows, consisting of each customer's ID and arrival time, separated by a whitespace, in the order in which they arrive. Sample inputs are provided below.

The program assumes a constant service time of 1.0. Service time is provided by a Supplier<Double> in line 11 of `Main.java`, which may be edited accordingly. For instance, the line could be edited to: 
```
Supplier<Double> serviceTime = () -> sc.nextDouble();
```
In this case, additional rows should be provided in the input, with each row consisting of a service time, in the order in which service begins. The number of additional rows provided should correspond to the number of customers who are served.

## Output

Output consists of descriptions of each event in the simulation. Each row consists of the time the event occurred, followed by a written description of that event. The last row consists of the performance metrics computed by the simulation, i.e. average wait time for customers who have been served, number of customers served and number of customers who left without being served, in that order.

## Setup

#### Clone the Repository
```
git clone https://github.com/kevinkhoow/discrete-event-sim.git
cd discrete-event-sim
```

### Create Input File
```
```

#### Compile and Execute Program
```
javac *.java
```
