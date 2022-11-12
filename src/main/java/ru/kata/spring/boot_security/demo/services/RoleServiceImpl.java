package ru.kata.spring.boot_security.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void saveAll(Set<Role> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public RoleDTO convertToRoleDTO(Role role) {
        return mapper.map(role, RoleDTO.class);
    }
}
