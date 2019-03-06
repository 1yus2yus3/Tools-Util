/**
 * Copyright (C), 杭州未智科技有限公司
 *
 * @author: Cola
 * @date: 2019/02/22 11:29
 * @description:
 */
public class MyData {

    volatile static int j=0;

    public static void add(){
        try{
            Thread.sleep(10L);
        }catch (Exception e){

        }

        j++;
        System.out.println(Thread.currentThread().getName()+"加方法：j="+j);
    }

    public static void sub(){
        try{
            Thread.sleep(10L);
        }catch (Exception e){

        }
        j--;
        System.out.println(Thread.currentThread().getName()+"减方法：j="+j);
    }

    public static void getData(){
        System.out.println("j="+j);
    }
}
