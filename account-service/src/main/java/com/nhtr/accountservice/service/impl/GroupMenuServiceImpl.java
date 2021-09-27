package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.repository.GroupMenuRepository;
import com.nhtr.accountservice.service.GroupMenuService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GroupMenuServiceImpl implements GroupMenuService {

    private final GroupMenuRepository groupMenuRepository;

    @Override
    public MenuResponse getMenu() {
        MenuResponse response = MenuResponse
                .builder()
                .result(ApiResult.OK)
                .build();
        response.setContext(groupMenuRepository.findAll());
        return response;
    }
}
