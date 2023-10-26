package ru.mirea.gosha.TaskTwo;

public class Patient implements Runnable {
    private final Clinic clinic;
    private final String name;

    public Patient(String name, Clinic clinic) {
        this.name = name;
        this.clinic = clinic;
    }

    @Override
    public void run() {
        clinic.takeCare(this);
    }

    public String getName() {
        return name;
    }
}
