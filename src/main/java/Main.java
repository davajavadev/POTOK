import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 50;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List< Callable<Void> > tasks  = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            final int index = i;
            tasks.add(()->{
                numbers.set(index, numbers.get(index)+10);
                return null;
            });
        }
        List<Future<Void>> futures = executorService.invokeAll(tasks);

        for (Future<Void> future : futures) {
            future.get();
        }
        executorService.shutdown();
        System.out.println(numbers);
    }

}
