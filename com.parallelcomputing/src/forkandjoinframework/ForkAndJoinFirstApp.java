package forkandjoinframework;

import java.util.concurrent.ForkJoinPool;

public class ForkAndJoinFirstApp {
    /**
     * fork() - asynchronously executes the given tasks in the pool.
     *      We can call it when using RecursiveTask<T> Or Recursive action
     *
     * join() - returns the result of the computation when it’s finished
     *
     * invoke() -  executes the given task + wait + return the result upon completion
     */
    public static void main(String[] args) {

        // ExecutorService
        // automatically determines the number of threads by default (based from processor)
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 200 = just for demo purposes
        SimpleRecursiveAction action = new SimpleRecursiveAction(200);
        // waits for the threads
        action.invoke();
    }

    /**
     * OUTPUT:
     *
     * The 200 is splitted into two halves
     *
     * If its small enough like 60, it will not split
     *
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=53024:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_341.jdk/Contents/Home/jre/lib/rt.jar:/Users/joli/Developer/workspace-learning/java/multithreading-playground/out/production/com.parallelcomputing forkandjoinframework.ForkAndJoinFirstApp
     * Parallel execution and split the tasks . . .
     * Sequential execution and split the tasks . . .
     * The size of this task 100
     * Sequential execution and split the tasks . . .
     * The size of this task 100
     */
}
