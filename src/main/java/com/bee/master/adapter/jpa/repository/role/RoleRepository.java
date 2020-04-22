package com.bee.master.adapter.jpa.repository.role;

import com.bee.master.application.vo.PageVO;
import com.bee.master.application.vo.RoleVO;

public interface RoleRepository {

    PageVO<RoleVO> page(Integer pageIndex, Integer pageSize, String sort, String filterTitle);
}
