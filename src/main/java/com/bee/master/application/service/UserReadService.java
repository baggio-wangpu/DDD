package com.bee.master.application.service;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.adapter.jpa.repository.StudentJpaRepository;
import com.bee.master.adapter.jpa.repository.TeacherJpaRepository;
import com.bee.master.application.request.LoginRequest;
import com.bee.master.application.vo.LoginVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.bee.master.common.RoleType.STUDENT;
import static com.bee.master.common.RoleType.TEACHER;

@Slf4j
@Service
@AllArgsConstructor
public class UserReadService {

    private final UserClient userClient;
    private final TeacherJpaRepository teacherJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public LoginVO login(LoginRequest loginRequest) {
        LoginVO loginVO = userClient.login(loginRequest);
        String roles = getUserRoles(loginVO.getUserInfo().getId());
        loginVO.setRoles(roles);
        return loginVO;
    }

    private String getUserRoles(String userId) {
        boolean hasTeacherRole = teacherJpaRepository.findById(userId).isPresent();
        boolean hasStudentRole = studentJpaRepository.findById(userId).isPresent();
        if (hasTeacherRole && hasStudentRole) {
            return String.join(",", TEACHER.name(), STUDENT.name());
        }
        if (hasTeacherRole) {
            return TEACHER.name();
        }
        if (hasStudentRole) {
            return STUDENT.name();
        }
        return "";
    }
}
