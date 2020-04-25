package com.bee.master.application.mapper;

import com.bee.master.adapter.jpa.entity.RolePO;
import com.bee.master.application.vo.RoleVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenericMapper {
    RoleVO poToVO(RolePO rolePO);
}
