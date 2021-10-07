package com.nhtr.accountservice.mapper;

import com.nhtr.accountservice.client.model.GroupMenuDto;
import com.nhtr.accountservice.entity.GroupMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMenuDtoMapper extends DomainMapper<GroupMenuDto, GroupMenu> {
}
