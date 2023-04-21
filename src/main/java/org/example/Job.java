package org.example;
// ini contoh penggunaan lombok
import lombok.Data;

@Data
class Job {
    private final String job;
    private String position;
    private double salary;
}