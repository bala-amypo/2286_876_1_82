package com.example.demo.util;

public class ProductivityCalcultor {
    public static double computerScore(double hours, int tasks, int meetings){
        double score = (hours * 10) + (tasks * 5) + (meetings * 2);
        return Math.max(0.0,Math.min(100.0,score));
    }
}