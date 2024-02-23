package collections.synchronization;

import java.util.concurrent.Exchanger;

class FirstExchangeThread implements Runnable {
    private int counter = 0;
    // Integer is the type of data we want to exchange
    private Exchanger<Integer> exchanger;

    public FirstExchangeThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter++;
            System.out.println("First Thread incremented the counter: " + counter);

            try {
                // will be in blocked state waiting for other thread to respond
                counter = exchanger.exchange(counter);
                System.out.println("First thread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class SecondExchangeThread implements Runnable {
    private int counter = 0;
    // Integer is the type of data we want to exchange
    private Exchanger<Integer> exchanger;

    public SecondExchangeThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true) {
            counter--;
            System.out.println("Second Thread decremented the counter: " + counter);

            try {
                // will be in blocked state waiting for other thread to respond
                counter = exchanger.exchange(counter);
                System.out.println("Second thread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        FirstExchangeThread t1 = new FirstExchangeThread(exchanger);
        SecondExchangeThread t2 = new SecondExchangeThread(exchanger);

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
