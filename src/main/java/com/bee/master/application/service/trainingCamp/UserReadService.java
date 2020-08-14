package com.bee.master.application.service.trainingCamp;

import com.bee.master.adapter.client.UserClient;
import com.bee.master.adapter.jpa.repository.trainingCamp.TraineeJpaRepository;
import com.bee.master.adapter.jpa.repository.trainingCamp.TrainerJpaRepository;
import com.bee.master.application.request.trainingCamp.LoginRequest;
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
    private final TrainerJpaRepository trainerJpaRepository;
    private final TraineeJpaRepository traineeJpaRepository;

    public LoginVO login(LoginRequest loginRequest) {
        LoginVO loginVO = userClient.login(loginRequest);
        List<String> roles = getUserRoles(loginVO.getUserInfo().getId());
        loginVO.setAuthorities(roles);
        return loginVO;
    }

    private List<String> getUserRoles(String userId) {
        boolean hasTeacherRole = trainerJpaRepository.findById(userId).isPresent();
        boolean hasStudentRole = traineeJpaRepository.findById(userId).isPresent();
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
