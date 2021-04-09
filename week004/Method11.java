import java.util.concurrent.CompletableFuture;

public class Method11 {
    public static void main(String[] args) {

        String result = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e->{                // Fluent API
            System.out.println(e.getMessage());
            return "Hello world!";
        }).join();
        System.out.println(result);


    }
}
