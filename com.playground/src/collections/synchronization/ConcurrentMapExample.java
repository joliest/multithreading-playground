package collections.synchronization;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstMapWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public FirstMapWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z", 5);
            map.put("A", 25);
            Thread.sleep(2000);
            map.put("D", 19);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondMapWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public SecondMapWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(2000);
            System.out.println(map.get("Z"));
            System.out.println(map.get("B"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        FirstMapWorker first = new FirstMapWorker(map);
        SecondMapWorker second = new SecondMapWorker(map);

        new Thread(first).start();
        new Thread(second).start();
    }
}
