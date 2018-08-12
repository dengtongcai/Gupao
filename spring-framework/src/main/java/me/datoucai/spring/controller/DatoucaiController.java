package me.datoucai.spring.controller;

import me.datoucai.spring.annotation.CcAutowired;
import me.datoucai.spring.annotation.CcController;
import me.datoucai.spring.annotation.CcRequestMapping;
import me.datoucai.spring.service.PrintService;

@CcController
@CcRequestMapping(value = "/datoucai")
public class DatoucaiController {

    @CcAutowired
    PrintService printService;

    @CcRequestMapping(value = "/req")
    public String getReq() {
        printService.print();
        return "";
    }
}
