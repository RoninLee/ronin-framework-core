package com.ronin.basemodel.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizelong
 * @date Created on 2020/7/7 10:36
 * @description 放开权限的url
 */
public class PermitAllUrl {

    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {"/actuator/health", "/actuator/env", "/actuator/metrics/**", "/actuator/trace", "/actuator/dump",
            "/actuator/jolokia", "/actuator/info", "/actuator/logfile", "/actuator/refresh", "/actuator/flyway", "/actuator/liquibase",
            "/actuator/heapdump", "/actuator/loggers", "/actuator/auditevents", "/actuator/env/PID", "/actuator/jolokia/**",
            "/v2/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/oauth/verification/**",
            // openapi-anon/地址用于各服务开放匿名API前缀使用
            "/openapi-anon/**"};

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[0]);
    }
}
