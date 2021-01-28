package com.cy.rest;

import com.cy.api.vm.ResponseJsonVM;
import com.cy.common.util.ReqResUtils;
import com.cy.domain.Demo;
import com.cy.service.impl.TranslateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@CrossOrigin(value = "${cy.cross-origin.access-control.allow-origin}")
@RestController
@RequestMapping("/test")
public class TestResource extends CommonResource{

    @Autowired
    private TranslateService translateService;

    @RequestMapping(value = "/1", method = {RequestMethod.POST})
    @ApiOperation(value = "", notes = "")
    @Transactional(rollbackFor = Exception.class)
    public String info(HttpServletRequest request, HttpServletResponse response) {
        String data = ReqResUtils.getStringNotNull(request, "data");
        Demo pass = (Demo) parseString2Clazz(data, Demo.class);
        pass = (Demo) translateService.translate(pass,Demo.class);
        return ResponseJsonVM.success(pass);
    }

}
