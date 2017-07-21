package slofast;

public class Racer implements Runnable {
	public static String winner;
	public void race(){
		for(int dist=1;dist<=50;dist++){
			System.out.println("distance covered by "+Thread.currentThread().getName()+" is "+dist+" meters");
			if(dist==50 && Thread.currentThread().getName().equals("hare")){
				try{
					System.out.println("HARE IS SLEEPING");
					Thread.sleep(1000*10);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			boolean isWon=this.isWon(dist);
			if(isWon){
				break;
			}
		}
	}
	public boolean isWon(int distance){
		boolean iswon;
		iswon=false;
		if(Racer.winner==null && distance==50){
			String winner_name=Thread.currentThread().getName();
			Racer.winner=winner_name;
			System.out.println("Race won by:"+Racer.winner);
			iswon=true;
		}
		else if(Racer.winner==null){
			iswon=false;
		}
		else if(Racer.winner!=null){
			iswon=true;
		}
		return iswon;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.race();
	}

}
