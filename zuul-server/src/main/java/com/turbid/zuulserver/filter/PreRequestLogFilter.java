package com.turbid.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;


public class PreRequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request= ctx.getRequest();
//        request.getHeader("Authorization");
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(401);
        return null;
    }
}
