package com.cy.rest;

import com.cy.api.vm.ResponseJsonVM;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CrossOrigin(value = "${cy.cross-origin.access-control.allow-origin}")
@RestController
@RequestMapping("/public")
public class PublicResource extends CommonResource {


    @RequestMapping(value = "/time/get", method = {RequestMethod.POST})
    @ApiOperation(value = "获取系统时间", notes = "获取系统时间")
    @Transactional(rollbackFor = Exception.class)
    public String info(HttpServletRequest request, HttpServletResponse response) {
        Date now = new Date();
        return ResponseJsonVM.success(now.getTime());
    }


}
