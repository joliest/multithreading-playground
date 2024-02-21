package collections.synchronization;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    public static void main(String[] args) {
        // thread safe
        BlockingQueue<DelayWorker> queue = new DelayQueue<>();
        try {
            // these can be inserted by different threads
            queue.put(new DelayWorker("This is the first message . . .", 2000)); // wait 2 seconds to execute...
            queue.put(new DelayWorker("This is the second message . . .", 1000));
            queue.put(new DelayWorker("This is the third message . . .", 4500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get the messages while queue is not empyu
        while(!queue.isEmpty()) {
//            take - will remove items from the queue
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class DelayWorker implements Delayed {
    // define the time given worker will be delayed
    private long duration;
    private String message;

    public DelayWorker(String message, long duration) {
        this.message = message;
        // make sure add System.currentTimeMillis()
        this.duration = System.currentTimeMillis() + duration;
    }

    @Override
    public int compareTo(Delayed otherObject) {
        // method that can compare object
        // may return -1, +1 or -
        if (duration < ((DelayWorker) otherObject).getDuration()) {
//            actual duration is smaller than other object duration
            return -1;
        }
        if (duration > ((DelayWorker) otherObject).getDuration()) {
//            actual duration is larger than other object duration
            return +1;
        }
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "message='" + message + '\'' +
                '}';
    }
}
