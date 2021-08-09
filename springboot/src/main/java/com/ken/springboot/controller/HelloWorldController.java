package com.ken.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
@Api(tags = "1.0.0-SNAPSHOT", description = "X", value = "X")
public class HelloWorldController {

    @GetMapping
    @ApiOperation(value = "x", notes = "x")
//    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = DataType.STRING, paramType = ParamType.QUERY, defaultValue = "xxx")})
    public String helloWorld() {
        return "hello world";
    }
}
