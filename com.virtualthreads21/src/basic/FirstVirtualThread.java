package basic;

public class FirstVirtualThread {
    public static void main(String[] args) {

        // first virtual thread will become virtual-0, next will be virtual-1, and so on...
        var builder = Thread.ofVirtual().name("virtual-", 0);

        // starts the thread
        var t1 = builder.start(new VirtualTask());
        var t2 = builder.start(new VirtualTask());

        // all virtual threads are daemon threads !!!
        // if there are no operational thread, the JVM will terminate
        // without this below, it will not run
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
