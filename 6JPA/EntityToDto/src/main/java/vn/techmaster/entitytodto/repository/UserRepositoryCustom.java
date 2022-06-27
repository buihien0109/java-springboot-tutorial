package vn.techmaster.entitytodto.repository;

import vn.techmaster.entitytodto.dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom{
    List<UserDto> getAllUserDtoByCreateQuery();
    List<UserDto> getAllUserDtoByCreateNamedQuery();
}
