package com.sumavision.tetris.spring.zuul;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.pre.ServletDetectionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.sumavision.tetris.spring.zuul.auth.filter.ApiFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.MultipartFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.android.ApiAndroidLoginFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.capture.ApiCaptureLoginFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.process.ApiProcessLoginFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.qt.ApiQtLoginFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.server.ApiServerLoginFilter;
import com.sumavision.tetris.spring.zuul.auth.filter.terminal.ApiTerminalLoginFilter;

@ComponentScan(basePackages = {"com.sumavision.tetris"})
//这个地方不能扫描user-feign中的filter
@ServletComponentScan(basePackages = {"com.sumavision.tetris.mvc.listener", "com.sumavision.tetris.spring.zuul"})
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients("com.sumavision.tetris")
@EnableDiscoveryClient
@SpringBootApplication
public class TetrisSpringZuulApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TetrisSpringZuulApplication.class, args);
	}
	
	/**
	 * 解决上传文件重置问题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月31日 上午11:51:29
	 */
	@Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory(){
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer(){
			@Override
			public void customize(Connector connector){
				if(connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>){
	                ((AbstractHttp11Protocol<?>)connector.getProtocolHandler()).setMaxSwallowSize(-1);
	            }
			}
        });
        return factory;
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TetrisSpringZuulApplication.class);
    }
	
	@Bean
	public ApiAndroidLoginFilter apiAndroidLoginFilter(){
		return new ApiAndroidLoginFilter();
	}
	
	@Bean
	public ApiCaptureLoginFilter apiCaptureLoginFilter(){
		return new ApiCaptureLoginFilter();
	}
	
	@Bean
	public ApiProcessLoginFilter apiProcessLoginFilter(){
		return new ApiProcessLoginFilter();
	}
	
	@Bean
	public ApiQtLoginFilter apiQtLoginFilter(){
		return new ApiQtLoginFilter();
	}
	
	@Bean
	public ApiServerLoginFilter apiServerLoginFilter(){
		return new ApiServerLoginFilter();
	}
	
	@Bean
	public ApiTerminalLoginFilter apiTerminalLoginFilter() {
		return new ApiTerminalLoginFilter();
	}
	
	@Bean
	public ServletDetectionFilter servletDetectionFilter(){
		return new MultipartFilter();
	}
	
	@Bean
	public ApiFilter apiFilter(){
		return new ApiFilter();
	}
	
}
