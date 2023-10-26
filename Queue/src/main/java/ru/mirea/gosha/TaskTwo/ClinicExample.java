package ru.mirea.gosha.TaskTwo;

import java.util.concurrent.CountDownLatch;

public class ClinicExample {
    public static void main(int totalPatients) {
        Clinic clinic = new Clinic();

        CountDownLatch latch = new CountDownLatch(totalPatients);

        for (int i = 1; i <= totalPatients; i++) {
            Patient patient = new Patient("Patient " + i, clinic);
            Thread thread = new Thread(() -> {
                patient.run();
                latch.countDown();
            });
            thread.start();
        }

        try {
            latch.await(); // Ждем
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Max Queue Length: " + clinic.getMaxQueueLength());
    }
}