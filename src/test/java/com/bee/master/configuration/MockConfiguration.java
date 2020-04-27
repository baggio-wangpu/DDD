package com.bee.master.configuration;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.request.PasswordResetApplyRequest;
import com.bee.master.application.request.PasswordResetRequest;
import com.bee.master.application.request.RegisterRequest;
import com.bee.master.application.vo.LoginVO;
import com.bee.master.application.vo.UserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.validation.Valid;

@Configuration
public class MockConfiguration {

    @Primary
    @Bean(name = "mockedUserClient")
    public UserClient userClient(){
        return new MockedUserClient();
    }

    private static class MockedUserClient implements UserClient {

        @Override
        public LoginVO login(@Valid LoginRequest params) {
            UserVO userVO = UserVO.builder().id(params.getEmail()).email("test@email.com").build();
            return LoginVO.builder().token("token").userInfo(userVO).build();
        }

        @Override
        public void applyPasswordReset(@Valid PasswordResetApplyRequest params) {

        }

        @Override
        public void updatePassword(String userId, @Valid PasswordResetRequest passwordResetRequest) {

        }

        @Override
        public UserVO getUserByResetKey(String resetKey) {
            return null;
        }

        @Override
        public UserVO signUp(@Valid RegisterRequest user) {
            return null;
        }
    }
}
