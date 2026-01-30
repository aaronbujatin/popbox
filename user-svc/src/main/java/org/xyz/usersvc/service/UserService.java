package org.xyz.usersvc.service;

import org.xyz.usersvc.dto.CustomerCreateReq;
import org.xyz.usersvc.dto.CustomerInfoResp;

public interface UserService {

    void createCustomer(CustomerCreateReq customerCreateReq);
    CustomerInfoResp getCustomerById(Long id);


}
