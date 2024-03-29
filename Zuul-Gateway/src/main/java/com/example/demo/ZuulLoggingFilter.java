package com.example.demo;

import javax.servlet.http.HttpServletRequest;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.stereotype.Component;  
import com.netflix.zuul.ZuulFilter;  
import com.netflix.zuul.context.RequestContext;  
import com.netflix.zuul.exception.ZuulException;  

@Component
public class ZuulLoggingFilter extends ZuulFilter {
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	
	public boolean booleanshouldFilter() {  
	return true; //executing filter for every request  
	}  
	
	//log the content of the request  
	@Override  
	public Object run() throws ZuulException {  
	//getting the current HTTP request that is to be handle  
	HttpServletRequest request=RequestContext.getCurrentContext().getRequest();  
	//prints the detail of the requestin the log  
	logger.info("request -> {} request uri-> {}", request, request.getRequestURI());  
	return null;  
	} 
	
	@Override  
	public String filterType() {  
	return "pre"; //intercept all the request before execution  
	}  
	
	public int intfilterOrder()   
	{  
	return 1; //setting filter order to 1  
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}  
}
