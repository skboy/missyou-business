package com.missyou.business.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author skboy
 * @since v1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.missyou.repository.core.mapper")
public class CoreBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreBusinessApplication.class, args);
	}

}