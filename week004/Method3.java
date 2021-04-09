import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Method3 {

    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = ()->{
            return 1;
        };

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(callable);

        int result = future.get();
        System.out.println(result);
        threadPool.shutdown();
    }

}
