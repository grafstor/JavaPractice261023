package ru.mirea.gosha.TaskOne;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MFCExample {
    public static void main(int totalCitizens) {
        MFC mfc = new MFC();

        CountDownLatch latch = new CountDownLatch(totalCitizens);

        for (int i = 0; i < totalCitizens; i++) {
            String category = getRandomCategory();
            Citizen citizen = new Citizen(category, mfc);
            Thread thread = new Thread(() -> {
                citizen.run();
                latch.countDown();
            });
            thread.start();
        }

        try {
            latch.await(); // Ждем
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Angry Young Citizens: " + mfc.getAngryYoungCount());
        System.out.println("Angry Elderly Citizens: " + mfc.getAngryElderlyCount());
        System.out.println("Angry Business Citizens: " + mfc.getAngryBusinessCount());
    }

    private static String getRandomCategory() {
        Random random = new Random();
        int categoryNumber = random.nextInt(3);
        if (categoryNumber == 0) {
            return "young";
        } else if (categoryNumber == 1) {
            return "elderly";
        } else {
            return "business";
        }
    }
}