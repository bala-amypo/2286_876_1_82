package com.example.demo.util;

public class ProductivityCalculator {
    
    public static double computeScore(double hoursLogged, int tasksCompleted, int meetingsAttended) {
        hoursLogged = Math.max(0, hoursLogged);
        tasksCompleted = Math.max(0, tasksCompleted);
        meetingsAttended = Math.max(0, meetingsAttended);
        
        double score = (hoursLogged * 10) + (tasksCompleted * 5) + (meetingsAttended * 2);
        
        return Math.min(100.0, Math.max(0.0, score));
    }
}