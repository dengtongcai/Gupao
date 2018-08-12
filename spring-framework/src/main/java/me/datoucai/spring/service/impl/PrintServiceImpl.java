package me.datoucai.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.datoucai.spring.annotation.CcService;
import me.datoucai.spring.service.PrintService;

@CcService
@Slf4j
public class PrintServiceImpl implements PrintService {
    @Override
    public void print() {
        log.info("PrintService print");
    }
}
