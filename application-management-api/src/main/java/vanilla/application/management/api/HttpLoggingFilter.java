package vanilla.application.management.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpLoggingFilter implements Filter {

    @Value("${log.http.enabled}")
    private boolean enabled;

    @Value("${log.http.header}")
    private boolean showHeader;

    @Value("${log.http.request}")
    private boolean showRequest;

    @Value("${log.http.request-body}")
    private boolean showRequestBody;

    @Value("${log.http.response-body}")
    private boolean showResponseBody;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        ContentCachingRequestWrapper reqWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper resWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(reqWrapper, resWrapper);

        long end = System.currentTimeMillis();

        if (enabled) {
            Map<String, String> headers = getHeaders(reqWrapper);
            String uri = reqWrapper.getRequestURI();
            String method = reqWrapper.getMethod();
            String parameters = getRequestParams(reqWrapper);
            String requestBody = getRequestBody(reqWrapper);
            String responseBody = getResponseBody(resWrapper);
            long elapsedTime = end - start;
            
            StringBuffer logging = new StringBuffer();
            logging.append("\n");
            logging.append("[===== HTTP LOGGING =====]");
            logging.append(method).append(" ").append(uri).append(" - ").append(resWrapper.getStatus()).append(" (").append(elapsedTime / 1000.0).append("s)");
            
            if (showHeader) {
                logging.append("\n").append("Headers: ").append(headers.toString());
            }
            
            if (showRequest) {
                logging.append("\n").append("Parameters: ").append(parameters);
            }
            
            if (showRequest && showRequestBody) {
                logging.append("\n").append("Request Payload: ").append(requestBody);
            }
            
            if (showResponseBody) {
                logging.append("\n").append("Response Payload: ").append(responseBody);
            }
            
            log.info(logging.toString());
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<String, String>();
        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            headers.put(name, request.getHeader(name));
        }

        return headers;
    }
    
    private String getRequestParams(HttpServletRequest request) {
        String parameters = null;
        
        ContentCachingRequestWrapper reqWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        Enumeration<String> names = reqWrapper.getParameterNames();
        
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String val = reqWrapper.getParameter(name);
            
            if (parameters == null) {
                parameters = name + "=" + val;
            } else {
                parameters += "&" + name + "=" + val;;
            }
        }
        
        return parameters;
    }

    private String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper reqWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);

        if (reqWrapper != null) {
            byte[] buf = reqWrapper.getContentAsByteArray();

            if (buf.length > 0) {
                try {
                    return new String(buf, 0, buf.length, reqWrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    log.warn(e.getMessage());
                    return " - ";
                }
            }
        }

        return " - ";
    }

    private String getResponseBody(HttpServletResponse response) {
        String payload = null;
        ContentCachingResponseWrapper resWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);

        if (resWrapper != null) {
            byte[] buf = resWrapper.getContentAsByteArray();

            if (buf.length > 0) {
                try {
                    payload = new String(buf, 0, buf.length, resWrapper.getCharacterEncoding());
                    resWrapper.copyBodyToResponse();
                } catch (UnsupportedEncodingException e) {
                    log.warn(e.getMessage());
                    payload = " - ";
                } catch (IOException e) {
                    log.warn(e.getMessage());
                    payload = " - ";
                }
            }
        }

        return payload == null ? " - " : payload;
    }
}
