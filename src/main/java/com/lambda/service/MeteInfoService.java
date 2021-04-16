package com.lambda.service;

import javax.servlet.http.HttpServletResponse;

public interface MeteInfoService {

    String selectList();
    String queryListLambda();
    String excel(HttpServletResponse re);
}
