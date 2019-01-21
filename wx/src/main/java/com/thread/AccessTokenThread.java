package com.thread;


public class AccessTokenThread extends Thread {

	private boolean stop = false;
	
	@Override
	public void run() {
		while(!stop){
		}
	}
	
	public void shutdown(){
		stop = true;
	}
}
