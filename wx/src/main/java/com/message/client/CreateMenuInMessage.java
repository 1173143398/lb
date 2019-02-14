package com.message.client;

import java.util.List;

import com.message.IMessage;

public class CreateMenuInMessage implements IMessage{

	private List<Object> button;

	public List<Object> getButton() {
		return button;
	}

	public void setButton(List<Object> button) {
		this.button = button;
	}
	
}
