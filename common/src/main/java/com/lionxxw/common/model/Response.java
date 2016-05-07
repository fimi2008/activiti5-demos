package com.lionxxw.common.model;

import com.lionxxw.common.constants.DataStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class Response<T> implements Serializable {

	private static final long serialVersionUID = 7782981616682775073L;

	public Response(){
		
	}
	
	public Response(int status, String message, T data){
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	@Getter
	@Setter
	public int status = DataStatus.HTTP_SUCCESS;
	
	@Getter
	@Setter
	public String message;
	
	@Getter
	@Setter
	public T data;
	
	
	@Getter
	@Setter
	public T parentData;
	
}
