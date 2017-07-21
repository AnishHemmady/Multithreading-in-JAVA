package slofast;

import java.util.ArrayList;


class CurrentRelay implements Runnable{
	public static String Winner;
	
	public int distance;
	public static ArrayList<String> new_lst=new ArrayList<>();
	public static ArrayList<Integer> new_one=new ArrayList<>();
	public volatile boolean stopThread = false;
	public CurrentRelay(int count){
		this.distance=count;
	}
	
	public synchronized void see(int count){
		new_one.add(count);
	}
	
	
	public void run(){
		this.race();
	}
	
        
    
	private void race() {
		// TODO Auto-generated method stub
		Thread.yield();
		while(!stopThread){
			//System.out.println("CurrentThread:"+Thread.currentThread().getName()+"dist covered"+distance);
			if(distance==5){
				//System.out.println("Changing threads:inside join");
				try{
					String group=Thread.currentThread().getThreadGroup().getName();
					//System.out.println(group);
					if (group=="INDIA"){
						this.distance+=1;
						CurrentRelay crw1=new CurrentRelay(this.distance);
						stopThread=true;
						Relay.t2=new Thread(Relay.group1,crw1,"t2");
						Relay.t2.start();
						Relay.t2.join();
					}
					else if(group=="GERMANY"){
						this.distance+=1;
						CurrentRelay crw2=new CurrentRelay(this.distance);
						stopThread=true;
						Relay.t4=new Thread(Relay.group2,crw2,"t4");
						Relay.t4.start();
						Relay.t4.join();
					}
					else if(group=="USA"){
						this.distance+=1;
						CurrentRelay crw3=new CurrentRelay(this.distance);
						stopThread=true;
						Relay.t6=new Thread(Relay.group3,crw3,"t6");
						Relay.t6.start();
						Relay.t6.join();
					}
					else if(group=="AUSTRALIA"){
						this.distance+=1;
						CurrentRelay crw4=new CurrentRelay(this.distance);
						stopThread=true;
						Relay.t8=new Thread(Relay.group4,crw4,"t8");
						Relay.t8.start();
						Relay.t8.join();
					}
					else if(group=="RUSSIA"){
						this.distance+=1;
						CurrentRelay crw5=new CurrentRelay(this.distance);
						stopThread=true;
						Relay.t10=new Thread(Relay.group5,crw5,"t10");
						Relay.t10.start();
						Relay.t10.join();
					}
				}
				catch(InterruptedException e){
					e.printStackTrace();
					
				}
			}
			boolean iswon=this.won(distance);
			if(iswon==true){
				String group_name=Thread.currentThread().getThreadGroup().getName();
				if(group_name=="INDIA"||group_name=="USA"||group_name=="RUSSIA"||group_name=="AUSTRALIA"||
						group_name=="GERMANY"){
					
						stopThread=true;
					
				}
				
			}
			
	
			this.distance++;
		}
		
		
	}
	public boolean won(int dist){
		boolean iswon;
		iswon=false;
		if(CurrentRelay.Winner==null && dist==10){
			//String team_member=Thread.currentThread().getName();
			String team=Thread.currentThread().getThreadGroup().getName();
			//CurrentRelay.new_lst.add(team_member);
			CurrentRelay.new_lst.add(team);
			//CurrentRelay.Winner=team;
			Relay.increment();
			see(Relay.winner_count);
			//CurrentRelay.Winner=team;
			//System.out.println("team won"+Relay.winner_count+"is:"+team);
			iswon=true;
			
		}
		else if(CurrentRelay.Winner==null){
			iswon=false;
		}
		else if(CurrentRelay.Winner!=null){
			iswon=true;
		}
		return iswon;
	}
}
public class Relay {
	public static Thread t2;
	public static Thread t4;
	public static Thread t6;
	public static Thread t8;
	public static Thread t10;
	public static ThreadGroup group1;
	public static ThreadGroup group2;
	public static ThreadGroup group3;
	public static ThreadGroup group4;
	public static ThreadGroup group5;
	public static int counter=0;
	public static int winner_count=0;
	public synchronized static  void  increment(){
		winner_count++;
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		CurrentRelay cr1=new CurrentRelay(0);
		CurrentRelay cr2=new CurrentRelay(0);
		CurrentRelay cr3=new CurrentRelay(0);
		CurrentRelay cr4=new CurrentRelay(0);
		CurrentRelay cr5=new CurrentRelay(0);
		Relay.group1=new ThreadGroup("INDIA");
		Relay.group2=new ThreadGroup("GERMANY");
		Relay.group3=new ThreadGroup("USA");
		Relay.group4=new ThreadGroup("AUSTRALIA");
		Relay.group5=new ThreadGroup("RUSSIA");
		Thread t1=new Thread(group1,cr1,"t1");
		
		Thread t3=new Thread(group2,cr2,"t3");
		
		Thread t5=new Thread(group3,cr3,"t5");
		
		Thread t7=new Thread(group4,cr4,"t7");
		
		Thread t9=new Thread(group5,cr5,"t9");
		
		t1.start();
		//t2.start();
		t3.start();
		//t4.start();
		t5.start();
		//t6.start();
		t7.start();
		//t8.start();
		t9.start();
		//t10.start();
		t1.join();
		t3.join();
		t5.join();
		t7.join();
		t9.join();
		int i=0;
		for(String g:CurrentRelay.new_lst){
			System.out.println(g+"won-->"+CurrentRelay.new_one.get(i)+" place");
			i+=1;
			
		}
		
		
		
		
	}
}
