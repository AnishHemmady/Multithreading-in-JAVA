package slofast;
import java.lang.Thread.State;
class YieldRunnable implements Runnable{
	public void run(){
		System.out.println("Current-Thread-Start-"+Thread.currentThread().getName()+"Priority is "+Thread.currentThread().getPriority());
		//Thread.yield();
		System.out.println("Current-Thread-End-"+Thread.currentThread().getName()+"Priority is "+Thread.currentThread().getPriority());
	}
}
public class Yielddemo {
	public static void main(String[] args){
		YieldRunnable yr=new YieldRunnable();
		ThreadGroup allthreads=new ThreadGroup("ALLTHREADS");
		Thread t1=new Thread(allthreads,yr,"t1");
		Thread t2=new Thread(allthreads,yr,"t2");
		Thread t3=new Thread(allthreads,yr,"t3");
		Thread t4=new Thread(allthreads,yr,"t4");
		Thread t5=new Thread(allthreads,yr,"t5");
		Thread t6=new Thread(allthreads,yr,"t6");
		Thread t7=new Thread(allthreads,yr,"t7");
		Thread t8=new Thread(allthreads,yr,"t8");
		Thread t9=new Thread(allthreads,yr,"t9");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		t4.setPriority(Thread.NORM_PRIORITY);
		t5.setPriority(Thread.NORM_PRIORITY);
		t6.setPriority(Thread.NORM_PRIORITY);
		t7.setPriority(Thread.MIN_PRIORITY);
		t8.setPriority(Thread.MIN_PRIORITY);
		t9.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		
		while(true){
			State t1state=t1.getState();
			State t2state=t2.getState();
			State t3state=t3.getState();
			State t4state=t4.getState();
			State t5state=t5.getState();
			State t6state=t6.getState();
			State t7state=t7.getState();
			State t8state=t8.getState();
			State t9state=t9.getState();
			System.out.println(System.nanoTime()+"t1-STATUS:->"+t1state);
			System.out.println(System.nanoTime()+"t2-STATUS:->"+t2state);
			System.out.println(System.nanoTime()+"t3-STATUS:->"+t3state);
			System.out.println(System.nanoTime()+"t4-STATUS:->"+t4state);
			System.out.println(System.nanoTime()+"t5-STATUS:->"+t5state);
			System.out.println(System.nanoTime()+"t6-STATUS:->"+t6state);
			System.out.println(System.nanoTime()+"t7-STATUS:->"+t7state);
			System.out.println(System.nanoTime()+"t8-STATUS:->"+t8state);
			System.out.println(System.nanoTime()+"t9-STATUS:->"+t9state);
			int actve_threads=allthreads.activeCount();
			if(actve_threads==0){
				break;
			}
			
		}
	}

}
