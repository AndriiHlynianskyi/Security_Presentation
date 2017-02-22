package com.ahl.jwt.controller;

import com.ahl.jwt.security.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @RequestMapping(value="/api/hello", method=RequestMethod.GET)
    public @ResponseBody UserDetails get(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }
    @RequestMapping(value="/api/protected", method=RequestMethod.GET)
    @PreAuthorize("@authorizationServiceImpl.hasAccess(authentication, 'ROLE')")
    public @ResponseBody String getProtected() {
        return "PROTECTED INFO";
    }
}