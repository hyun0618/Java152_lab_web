package com.itwill.lab04.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FilterEx
 */
// 필터 요청 주소 매핑 설정:
// (1) web.xml(deployment descriptor) 파일에서 <filter>, <filter-mapping> 태그에서 설정하거나
// (2) @WebFilter 애너테이션으로 설정.
// web.xml과 애너테이션을 중복으로 설정하면 안된다. 
// 필터 체인이 있을 때, 필터들이 실행되는 순서는 web.xml에 설정된 순서대로 실행된다.
public class FilterEx extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterEx() {
    	System.out.println("FilterEx 생성");
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
    	System.out.println("FilterEx::destroy() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		   throws IOException, ServletException {
    	System.out.println("FilterEx chain.doFilter() 호출 전");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		System.out.println("FilterEx chain.doFilter() 호출 후");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("FilterEx::init() 호출");
	}

}
