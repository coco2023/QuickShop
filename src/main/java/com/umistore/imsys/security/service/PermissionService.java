package com.umistore.imsys.security.service;

import com.umistore.imsys.security.respository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<String> getPermissionsByRoleNames(List<String> roleNames) {
        return permissionRepository.findPermissionsByRoleNames(roleNames).stream()
                .collect(Collectors.toList());
    }

}
