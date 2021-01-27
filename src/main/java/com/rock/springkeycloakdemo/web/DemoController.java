package com.rock.springkeycloakdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
    @RequestMapping(value="/")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value="/secure")
    public String secure(Model model) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        String dob = "";

        if (principal instanceof KeycloakPrincipal) {

            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext()
                .getIdToken();

            Map<String, Object> customClaims = token.getOtherClaims();
            //System.out.println("It's definitely a Keycloak principal, here's the claims:");
            //for (Map.Entry<String, Object> entry : map.entrySet()) {
            //   System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            //}

            if (customClaims.containsKey("DOB")) {
                dob = String.valueOf(customClaims.get("DOB"));
            }
        }
        model.addAttribute("username", principal.getName());
        model.addAttribute("dob", dob);
        return "secure";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "home";
    }
}
