import java.util.Random;
import java.util.concurrent.*;

public class Method6 {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task = new FutureTask((Callable<Integer>) () -> new Random().nextInt());
        executor.submit(task);

        try {
            System.out.println("result: " + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
