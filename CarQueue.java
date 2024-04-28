import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarQueue {
    private Queue<Integer> queue;
    private Lock lock;
    
    
    public CarQueue() {
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        addToQueue(); 
        }


	public void addToQueue() {
		Runnable r = () -> {
            try {
                while (true) {
                    Random rand = new Random();
                    
                    lock.lock();
                    try {
                    	
                        queue.add(rand.nextInt(4));
                        
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
    }
	
	public int deleteQueue() {
        lock.lock();
        try {
        	
        	if(queue.isEmpty()) {
        		return -1;
        	}
        	else {
        		return queue.poll();
        	}
        } finally {
            lock.unlock();
        }
    }}
		
	
    



