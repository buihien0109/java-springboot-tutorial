package vn.techmaster.jpablog.model.mapper;

import org.mapstruct.*;
import vn.techmaster.jpablog.entity.User;
import vn.techmaster.jpablog.model.dto.UserDto;
import vn.techmaster.jpablog.model.dto.UserInfo;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface Mapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);


    @Mapping(source = "identityCardExpried", target = "identityCard.expried")
    @Mapping(source = "identityCardIssued", target = "identityCard.issued")
    User userInfoToUser(UserInfo userInfo);

    @InheritInverseConfiguration(name = "userInfoToUser")
    UserInfo userToUserInfo(User user);

    @InheritConfiguration(name = "userInfoToUser")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserInfo(UserInfo userInfo, @MappingTarget User user);
}
