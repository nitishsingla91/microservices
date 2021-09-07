package com.nagarro.nes.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter {

	Logger Logger = LoggerFactory.getLogger(AuthFilter.class);

	@Value("${auth.key}")
	private String authKey;

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String authHeaderKey = ctx.getRequest().getHeader("Authorization");
		if (authHeaderKey == null || !authHeaderKey.equalsIgnoreCase(authKey)) {
			String msgError = "Invalid Authorization header key";
			ctx.setResponseBody(msgError);
			ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
			ctx.setSendZuulResponse(false);
			Logger.debug(msgError);
			return null;
		}

		return null;
	}

	@Override
	public boolean shouldFilter() {
		System.out.println("should filter");
		return true;
	}

	@Override
	public int filterOrder() {

		return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}