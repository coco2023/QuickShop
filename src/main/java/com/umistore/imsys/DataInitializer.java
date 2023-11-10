//package com.umistore.imsys;
//
//import com.umistore.imsys.security.entity.Permission;
//import com.umistore.imsys.security.entity.RolePermission;
//import com.umistore.imsys.security.respository.PermissionRepository;
//import com.umistore.imsys.security.respository.RolePermissionRepository;
//import com.umistore.imsys.security.respository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PermissionRepository permissionRepository;
//    @Autowired
//    private RolePermissionRepository rolePermissionRepository;
//
//    @Override
//    public void run(String... args) {
//        // Create Permissions
//        Permission readProducts = Permission.builder().permissionName("READ_PRODUCTS").description("READ_PRODUCTS").build();
//        Permission createProduct = Permission.builder().permissionName("CREATE_PRODUCTS").description("CREATE_PRODUCTS").build();
//
//        permissionRepository.save(readProducts);
//        permissionRepository.save(createProduct);
//
//        // Assign Permissions to Roles
//        rolePermissionRepository.save(new RolePermission(null, 1, readProducts.getPermissionID()));
//        rolePermissionRepository.save(new RolePermission(null, 1, createProduct.getPermissionID()));
//        rolePermissionRepository.save(new RolePermission(null, 2, readProducts.getPermissionID()));
//
//    }
//
//}