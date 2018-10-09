package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class DemoApplicationzipkinServer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationzipkinServer.class, args);
	}
}
