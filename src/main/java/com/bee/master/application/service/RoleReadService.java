package com.bee.master.application.service;

import com.bee.master.adapter.jpa.repository.RoleJpaRepository;
import com.bee.master.application.mapper.RoleMapper;
import com.bee.master.application.request.PaginationRequest;
import com.bee.master.application.vo.PageVO;
import com.bee.master.application.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleReadService {

    private final RoleJpaRepository roleJpaRepository;

    RoleMapper roleMapper;

    public PageVO<RoleVO> page(PaginationRequest pagination) {
        return PageVO.from(roleJpaRepository.findAll(pagination.toPageable()), roleMapper);
    }
}


