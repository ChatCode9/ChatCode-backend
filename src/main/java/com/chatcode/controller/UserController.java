package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.domain.entity.User;
import com.chatcode.repository.user.UserReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserReadRepository userReadRepository;

    @GetMapping("/login")
    public ResponseEntity<?> test() {
        final String loginButtonHtmlText = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + "<title>Login Page</title>\n" + "<style>\n" + "    .login-button {\n" + "        padding: 10px 20px;\n" + "        font-size: 16px;\n" + "        text-align: center;\n" + "        cursor: pointer;\n" + "        outline: none;\n" + "        color: #fff;\n" + "        background-color: #4CAF50;\n" + "        border: none;\n" + "        border-radius: 5px;\n" + "        box-shadow: 0 9px #999;\n" + "        width: 200px;\n" + "        display: block;\n" + "        margin: 10px auto;\n" + "    }\n" + "\n" + "    .login-button:hover { background-color: #3e8e41 }\n" + "\n" + "    .login-button:active {\n" + "        background-color: #3e8e41;\n" + "        box-shadow: 0 5px #666;\n" + "        transform: translateY(4px);\n" + "    }\n" + "\n" + "    .google-button {\n" + "        background-color: #dd4b39;\n" + "    }\n" + "\n" + "    .google-button:hover {\n" + "        background-color: #c23321;\n" + "    }\n" + "\n" + "    .github-button {\n" + "        background-color: #333;\n" + "    }\n" + "\n" + "    .github-button:hover {\n" + "        background-color: #24292e;\n" + "    }\n" + "</style>\n" + "</head>\n" + "<body>\n" + "    <a href=\"/oauth2/authorization/google\" class=\"login-button google-button\">Login with Google</a>\n" + "    <a href=\"/oauth2/authorization/github\" class=\"login-button github-button\">Login with GitHub</a>\n" + "</body>\n" + "</html>\n";
        return ResponseEntity.ok(loginButtonHtmlText);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> test2(@AuthenticationPrincipal LoginUser loginUser) {
        String body = "<h2>Current User Info</h2> \n"
                + "<p>User id: " + loginUser.getId() + "</p>\n"
                + "<p>Username: " + loginUser.getName() + "</p>\n"
                + "<p>Role: " + loginUser.getAuthorities().toString() + "</p>\n"
                + "<p>Avatar id: " + loginUser.getAvatarId() + "</p>\n";
        return ResponseEntity.ok(body);
    }

    @GetMapping("")
    @Secured("ROLE_USER")
    public ResponseEntity<?> test3(@AuthenticationPrincipal LoginUser loginUser) {
        User user = userReadRepository.findById(loginUser.getId()).get();
        return ResponseEntity.ok(user);
    }

}
