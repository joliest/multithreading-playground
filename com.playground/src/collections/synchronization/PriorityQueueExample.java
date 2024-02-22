package collections.synchronization;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstPriorityQueueWorker implements Runnable {
    private BlockingQueue<Person> queue;

    public FirstPriorityQueueWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new Person(12, "Adam"));
            queue.put(new Person(55, "Kevin"));
            queue.put(new Person(27, "Ana"));
            Thread.sleep(2000);
            queue.put(new Person(31, "Daniel"));
            Thread.sleep(1000);
            queue.put(new Person(15, "Joe"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondPriorityQueueWorker implements Runnable {
    private BlockingQueue<Person> queue;

    public SecondPriorityQueueWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            // take() gets the ehad of the queue
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class PriorityQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        FirstPriorityQueueWorker first = new FirstPriorityQueueWorker(queue);
        SecondPriorityQueueWorker second = new SecondPriorityQueueWorker(queue);

        new Thread(first).start();
        new Thread(second).start();
    }
}
