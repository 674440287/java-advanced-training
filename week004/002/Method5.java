import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Method5 {

    public static void main(String[] args) {
        //第一种方式
        FutureTask<Integer> task = new FutureTask<>(() -> new Random().nextInt());
        new Thread(task).start();

        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
