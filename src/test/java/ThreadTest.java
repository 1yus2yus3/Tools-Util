
/**
 * Copyright (C), 杭州未智科技有限公司
 *
 * @author: Cola
 * @date: 2019/02/20 15:19
 * @description:
 */
public class ThreadTest {

    public static void main(String[] args) {

        final MyData data = new MyData();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    data.add();
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    data.sub();
                }
            }).start();
        }

        //ForkJoinTask

        try{
            Thread.sleep(5000L);
        }catch (Exception e){

        }
        data.getData();

    }
}
