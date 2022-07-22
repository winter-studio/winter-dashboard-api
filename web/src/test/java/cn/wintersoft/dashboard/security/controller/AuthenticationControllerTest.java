package cn.wintersoft.dashboard.security.controller;

import cn.wintersoft.dashboard.security.model.UserLoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import cn.wintersoft.dashboard.web.utils.Jackson;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
        UserLoginRequest loginRequest = new UserLoginRequest("admin", "123456");
        String s = Jackson.get().writeValueAsString(loginRequest);
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(s))
               .andDo(print()).andExpect(status().isOk())
               .andExpect(content().string(containsString("accessToken")));
    }
}
