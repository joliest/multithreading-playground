package basic;

public class FirstVirtualThread {
    public static void main(String[] args) throws InterruptedException {

        // first virtual thread will become virtual-0, next will be virtual-1, and so on...
        // Approach 2: added factory() at the end
        var factory = Thread.ofVirtual().name("virtual-", 0).factory();

        // starts the thread
        // Approach 2: No need to implement runnable to VirtualTask,
        // you can set run() as static
        var t1 = factory.newThread(VirtualTask::run);
        var t2 = factory.newThread(VirtualTask::run);

        // all virtual threads are daemon threads !!!
        // if there are no operational thread, the JVM will terminate
        // without this below, it will not run
        t1.start();
        t2.start();


        t1.join();
        t2.join();
    }
}
