package com.bee.master.application;

import com.bee.master.adapter.jpa.repository.role.RoleRepository;
import com.bee.master.application.request.PaginationRequest;
import com.bee.master.application.vo.PageVO;
import com.bee.master.application.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleApplicationService {

    private final RoleRepository roleRepository;

    public PageVO<RoleVO> page(PaginationRequest pagination, String filterTitle) {
        return roleRepository.page(pagination.getPageIndex(), pagination.getPageSize(), pagination.getSort(), filterTitle);
    }


}
