import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程解决哲学家进餐问题
 * 假设对哲学家按顺时针编号，哲学家右边的叉子与哲学家拥有相同编号
 *
 * @author dongyafei
 * @date 2021/11/23
 */
public class DiningPhilosophers {
    // 限制同时进餐的哲学家数量
    private final Semaphore eatLimit;
    // 一只叉子视为一个ReentrantLock
    private final ReentrantLock[] locks;

    public DiningPhilosophers() {
        // 限制同时最多四位哲学家进餐
        this.eatLimit = new Semaphore(4);
        this.locks = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            this.locks[i] = new ReentrantLock();
        }
    }

    /**
     * 一次尝试进餐行为
     * @param philosopher 哲学家编号
     * @throws InterruptedException 中断异常
     */
    public void tryToEat(int philosopher) throws InterruptedException {
        // 左右手叉子的编号
        int rightFork = philosopher;
        int leftFork = (philosopher + 1) % 5;

        StringBuilder p = new StringBuilder();
        for (int i = 0; i < philosopher; i++) {
            p.append("                    ");
        }
        p.append(philosopher);
        // 申请进餐，简餐期间，每个动作均设定了执行时间（用Thread.sleep()表示）
        this.eatLimit.acquire();
        this.locks[rightFork].lock();
        System.out.println(p + "号哲学家拿起了右边的叉子");
        Thread.sleep(20);
        this.locks[leftFork].lock();
        System.out.println(p + "号哲学家拿起了左边的叉子");
        Thread.sleep(20);
        System.out.println(p + "号哲学家开始用餐");
        Thread.sleep(2000);     // 进餐时间设定为2s
        System.out.println(p + "号哲学家用餐结束");
        this.locks[rightFork].unlock();
        System.out.println(p + "号哲学家放下了右边的叉子");
        Thread.sleep(20);
        this.locks[leftFork].unlock();
        System.out.println(p + "号哲学家放下了左边的叉子,完成了一次进餐");
        System.out.println(p + "*****************");
        Thread.sleep(20);
        // 完成进餐
        this.eatLimit.release();
    }

    // 哲学家对应的进程，休息随机时间，循环尝试用餐
    private static class PhilosopherThread implements Runnable {
        // 哲学家编号
        private final int philosopher;
        private final DiningPhilosophers diningPhilosophers;
        private final Random random = new Random();

        public PhilosopherThread(int philosopher, DiningPhilosophers diningPhilosophers) {
            this.philosopher = philosopher;
            this.diningPhilosophers = diningPhilosophers;
        }

        @Override
        public void run() {
            // 无限循环期间，每隔一段随机时间，发起一次进餐
            while (true) {
                try {
                    Thread.sleep(random.nextInt(1000));
                    this.diningPhilosophers.tryToEat(this.philosopher);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 主函数，测试执行结果
    public static void main(String[] args) {
        System.out.println("===========================================  程序开始  ===========================================");
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        // 创建五个哲学家进程
        PhilosopherThread[] threads = new PhilosopherThread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new PhilosopherThread(i, diningPhilosophers);
        }
        // 启动五个哲学家进程
        for (int i = 0; i < 5; i++) {
            new Thread(threads[i]).start();
        }
    }
}