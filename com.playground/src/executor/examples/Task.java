package executor.examples;


import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private int id;
    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id of " + id + " is in work - thread id: " + Thread.currentThread().getId());
        long duration = (long) (Math.random() * 5);
        // otherway in sleeping a thread
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}