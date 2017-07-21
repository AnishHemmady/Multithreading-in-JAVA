package slofast;
import java.util.*;
class Production{
	
	private LinkedList<Integer> list=new LinkedList<>();
	final int size=10;
	public void Producer() throws InterruptedException{
		int value=0;
		while(true){
			synchronized(this){
				while(list.size()==size){
					this.wait();
				}
				list.add(value++);
				this.notify();
			}
		}
	}
	public void Consumer() throws InterruptedException{
		while(true){
			synchronized(this){
				while(list.size()==0){
					this.wait();
				}
				System.out.println("List size "+list.size());
				int val=list.removeFirst();
				System.out.println("Value popped is "+val);
				this.notify();
			}
			Thread.sleep(200);
		}
		
	}
	
}

public class Prod_Cons {
	
	public static void main(String[] args) throws InterruptedException{
		final Production p=new Production();
		Thread t1=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					p.Producer();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		});
		Thread t2=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					p.Consumer();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
