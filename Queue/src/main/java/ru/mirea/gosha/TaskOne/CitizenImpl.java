package ru.mirea.gosha.TaskOne;

class Citizen implements Runnable {
    public final String category;
    private final MFC mfc;

    public Citizen(String category, MFC mfc) {
        this.category = category;
        this.mfc = mfc;
    }

    @Override
    public void run() {
        boolean serviced = mfc.service(this);
        if (!serviced) {
            System.out.println("Citizen left in anger - Category: " + category);
            mfc.incrementAngryCount(category);
        }
    }
}
