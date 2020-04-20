package com.bee.master.adapter.restful.controller;

import com.bee.master.adapter.jpa.entity.RoleEntity;
import com.bee.master.adapter.jpa.repository.RoleRepository;
import com.bee.master.application.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("public")
public class JpaDemoController {

    private final RoleRepository roleRepository;

    public JpaDemoController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping(path = "roles", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO put(@RequestBody RoleDTO roleDTO) {
        return new RoleDTO(roleRepository.save(new RoleEntity(roleDTO)));
    }

    @GetMapping("roles/{id}")
    public RoleDTO get(@PathVariable Long id) {
        return new RoleDTO(roleRepository.getOne(id));
    }
}
