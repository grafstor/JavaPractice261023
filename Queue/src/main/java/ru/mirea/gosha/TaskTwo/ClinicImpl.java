package ru.mirea.gosha.TaskTwo;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Clinic {
    private final Semaphore therapistSemaphore = new Semaphore(1);
    private final Semaphore mriSemaphore = new Semaphore(1);
    private final Queue<Patient> queue = new LinkedList<>();
    private int maxQueueLength = 0;

    public void takeCare(Patient patient) {
        queue.add(patient);

        maxQueueLength = Math.max(maxQueueLength, queue.size());

        try {
            therapistSemaphore.acquire();
            queue.remove(patient);
            System.out.println(patient.getName() + " is with the therapist.");
            Thread.sleep(new Random().nextInt(1000));
            therapistSemaphore.release();

            mriSemaphore.acquire();
            System.out.println(patient.getName() + " is getting an MRI.");
            Thread.sleep(new Random().nextInt(1000));
            mriSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getMaxQueueLength() {
        return maxQueueLength;
    }
}
