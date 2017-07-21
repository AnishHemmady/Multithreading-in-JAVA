package slofast;
import java.lang.Thread.State;
class JoinThread implements Runnable{
	public void run(){
		System.out.println("current thread at beginning:"+Thread.currentThread().getName());
		if(Thread.currentThread().getName().equals("firstthread")){
			try{
				System.out.println("inside join condition");
				JoinDemo.secondthread.start();
				JoinDemo.secondthread.join();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
public class JoinDemo {
	public static Thread firstthread;
	public static Thread secondthread;
	public static void main(String[] args){
		JoinThread jt=new JoinThread();
		firstthread=new Thread(jt,"firstthread");
		secondthread=new Thread(jt,"secondthread");
		firstthread.start();
		
		while(true){
			State frststate=firstthread.getState();
			State secondstate=secondthread.getState();
			System.out.println("STATUS:"+frststate);
			System.out.println("STATUS:"+secondstate);
			if(frststate.equals(State.TERMINATED)&& secondstate.equals(State.TERMINATED)){
				break;
			}
		}
		
		
	}
}
