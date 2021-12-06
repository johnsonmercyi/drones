package com.soft.test.utility;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Util {
    public static URI getPathUri(String path) {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path).toUriString());
    }
    
}
