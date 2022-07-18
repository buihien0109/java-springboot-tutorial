package vn.techmaster.entitytodto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.entitytodto.dto.UserDto;
import vn.techmaster.entitytodto.mapper.UserMapperImpl;
import vn.techmaster.entitytodto.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperImpl userMapper;

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }
}
