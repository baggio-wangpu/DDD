package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.jpa.entity.RolePO;
import com.bee.master.adapter.jpa.repository.RoleJpaRepository;
import com.bee.master.application.request.PaginationRequest;
import com.bee.master.application.service.RoleReadService;
import com.bee.master.application.vo.PageVO;
import com.bee.master.application.vo.RoleVO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("public")
public class JpaDemoController {

    private final RoleJpaRepository roleJpaRepository;

    private final RoleReadService roleReadService;

    @PostMapping(path = "roles", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RoleVO create(@RequestBody RoleVO roleDTO) {
        return new RoleVO(roleJpaRepository.save(new RolePO(roleDTO)));
    }

    @GetMapping("roles/{id}")
    public RoleVO get(@PathVariable Long id) {
        return new RoleVO(roleJpaRepository.getOne(id));
    }

    @GetMapping("roles")
    @ApiOperation(value = "Pagination demo")
    public PageVO<RoleVO> getAll(PaginationRequest pagination) {
        return roleReadService.page(pagination);
    }
}
