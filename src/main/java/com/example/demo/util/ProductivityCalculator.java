// package com.example.demo.util;

// public class ProductivityCalculator {
    
//     public static double computeScore(double hoursLogged, int tasksCompleted, int meetingsAttended) {
//         hoursLogged = Math.max(0, hoursLogged);
//         tasksCompleted = Math.max(0, tasksCompleted);
//         meetingsAttended = Math.max(0, meetingsAttended);
        
//         double score = (hoursLogged * 10) + (tasksCompleted * 5) + (meetingsAttended * 2);
        
//         return Math.min(100.0, Math.max(0.0, score));
//     }
// }
package com.example.demo.util;

public class ProductivityCalculator {
    public static double computeScore(double hours, int tasks, int meetings) {
        // Normalize negative values to 0
        hours = Math.max(0, hours);
        tasks = Math.max(0, tasks);
        meetings = Math.max(0, meetings);

        double score = (hours * 10) + (tasks * 5) + (meetings * 2);
        // Clamp to 0-100
        return Math.min(100.0, Math.max(0.0, score));
    }
}