package ru.mirea.gosha.TaskOne;
import java.util.Random;
class MFC {
    private boolean window1Busy = false;
    private boolean window2Busy = false;
    private boolean window3Busy = false;
    private int angryYoungCount = 0;
    private int angryElderlyCount = 0;
    private int angryBusinessCount = 0;

    public synchronized boolean service(Citizen citizen) {
        Random random = new Random();
        int windowNumber = random.nextInt(3) + 1;
        if (windowNumber == 1) {
            if (!window1Busy) {
                window1Busy = true;
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    window1Busy = false;
                }
                return true;
            }
        } else if (windowNumber == 2 && citizen.category.equals("elderly")) {
            if (!window2Busy) {
                window2Busy = true;
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    window2Busy = false;
                }
                return true;
            }
        } else if (windowNumber == 3 && citizen.category.equals("business")) {
            if (!window3Busy) {
                window3Busy = true;
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    window3Busy = false;
                }
                return true;
            }
        }
        return false;
    }

    public synchronized void incrementAngryCount(String category) {
        if (category.equals("young")) {
            angryYoungCount++;
        } else if (category.equals("elderly")) {
            angryElderlyCount++;
        } else if (category.equals("business")) {
            angryBusinessCount++;
        }
    }

    public int getAngryYoungCount() {
        return angryYoungCount;
    }

    public int getAngryElderlyCount() {
        return angryElderlyCount;
    }

    public int getAngryBusinessCount() {
        return angryBusinessCount;
    }
}

