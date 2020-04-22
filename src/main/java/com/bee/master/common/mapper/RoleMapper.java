package com.bee.master.common.mapper;

import com.bee.master.adapter.jpa.entity.RolePO;
import com.bee.master.application.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleVO entityToVo(RolePO rolePO);
}
