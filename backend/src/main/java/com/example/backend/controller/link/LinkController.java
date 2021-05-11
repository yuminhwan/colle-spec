package com.example.backend.controller.link;

import com.example.backend.response.CommonResult;
import com.example.backend.service.link.Linkservice;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4. Link"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LinkController {

    private final Linkservice linkservice;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "연동 로그인 ", notes = "아이디와 비밀번호를 입력하면 연동정보를 데이터베이스에 입력한다.")
    @GetMapping("/link")
    public CommonResult linksignin(@ApiParam(value = "어플 회원 아이디(id) ", required = true) @RequestParam String id,
                                   @ApiParam(value = "어플 회원 비밀번호(pass) ", required = true) @RequestParam String pass) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);


        int appidx = linkservice.linkLogin(id, pass);
        linkservice.linkInfo(appidx, idx);
        linkservice.linkGrade(appidx, idx);


        return responseService.getSuccessResultMsg("연동이 완료되었습니다.");

    }


}