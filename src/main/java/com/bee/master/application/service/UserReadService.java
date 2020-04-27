package com.bee.master.application.service;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.adapter.jpa.repository.StudentJpaRepository;
import com.bee.master.adapter.jpa.repository.TeacherJpaRepository;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.vo.LoginVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bee.master.common.RoleType.STUDENT;
import static com.bee.master.common.RoleType.TEACHER;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Slf4j
@Service
@AllArgsConstructor
public class UserReadService {

    private final UserClient userClient;
    private final TeacherJpaRepository teacherJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public LoginVO login(LoginRequest loginRequest) {
        LoginVO loginVO = userClient.login(loginRequest);
        List<String> roles = getUserRoles(loginVO.getUserInfo().getId());
        loginVO.setAuthorities(roles);
        return loginVO;
    }

    private List<String> getUserRoles(String userId) {
        boolean hasTeacherRole = teacherJpaRepository.findById(userId).isPresent();
        boolean hasStudentRole = studentJpaRepository.findById(userId).isPresent();
        if (hasTeacherRole && hasStudentRole) {
            return asList(TEACHER.name(), STUDENT.name());
        }
        if (hasTeacherRole) {
            return singletonList(TEACHER.name());
        }
        if (hasStudentRole) {
            return singletonList(STUDENT.name());
        }
        return emptyList();
    }
}
