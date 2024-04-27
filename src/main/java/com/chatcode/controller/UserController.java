package com.chatcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public ResponseEntity<?> test() {
        final String htmlBody = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + "<title>Login Page</title>\n" + "<style>\n" + "    .login-button {\n" + "        padding: 10px 20px;\n" + "        font-size: 16px;\n" + "        text-align: center;\n" + "        cursor: pointer;\n" + "        outline: none;\n" + "        color: #fff;\n" + "        background-color: #4CAF50;\n" + "        border: none;\n" + "        border-radius: 5px;\n" + "        box-shadow: 0 9px #999;\n" + "        width: 200px;\n" + "        display: block;\n" + "        margin: 10px auto;\n" + "    }\n" + "\n" + "    .login-button:hover { background-color: #3e8e41 }\n" + "\n" + "    .login-button:active {\n" + "        background-color: #3e8e41;\n" + "        box-shadow: 0 5px #666;\n" + "        transform: translateY(4px);\n" + "    }\n" + "\n" + "    .google-button {\n" + "        background-color: #dd4b39;\n" + "    }\n" + "\n" + "    .google-button:hover {\n" + "        background-color: #c23321;\n" + "    }\n" + "\n" + "    .github-button {\n" + "        background-color: #333;\n" + "    }\n" + "\n" + "    .github-button:hover {\n" + "        background-color: #24292e;\n" + "    }\n" + "</style>\n" + "</head>\n" + "<body>\n" + "    <a href=\"/oauth2/authorization/google\" class=\"login-button google-button\">Login with Google</a>\n" + "    <a href=\"/oauth2/authorization/github\" class=\"login-button github-button\">Login with GitHub</a>\n" + "</body>\n" + "</html>\n";
        return ResponseEntity.ok(htmlBody);
    }
}
