package com.v1;

public class Philosopher extends Thread{
    private final String name;
    private final Philosopher leftFork;
    private final Philosopher rightFork;

    public Philosopher(String name, Philosopher leftFork, Philosopher rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            think();
            synchronized (leftFork) {
                synchronized (rightFork) {
                    eat();
                }
            }
        }
    }

    private void think() {
        System.out.println(name + " is thinking");
    }

    private void eat() {
        System.out.println(name + " is eating");
    }
}
