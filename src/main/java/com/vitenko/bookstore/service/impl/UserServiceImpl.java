package com.vitenko.bookstore.service.impl;

import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.data.repository.UserRepository;
import com.vitenko.bookstore.exception.user.*;
import com.vitenko.bookstore.service.UserService;
import com.vitenko.bookstore.service.dto.UserDto;
import com.vitenko.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DataMapper dataMapper;

    private void validate(UserDto userDto) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException {
        if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
            if (userDto.getId() == null) {
                if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                    throw new UserEmailNotUniqueException("User with email " + userDto.getEmail() + " already exists.");
                }
            } else {
                    Optional<User> optionalSameEmail = userRepository.findByEmail(userDto.getEmail());
                    if (optionalSameEmail.isPresent()) {
                        User sameEmail =  optionalSameEmail.get();
                        if (!userDto.getId().equals(sameEmail.getId())) {
                            throw new UserEmailNotUniqueException("User with email " + userDto.getEmail()
                                    + " already exists.");
                        }
                    }
            }
        } else {
            throw new UserEmailWasNotProvidedException("User email wasn't provided.");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            throw new UserPasswordNotProvidedException("User password wasn't provided");
        }
    }

    @Override
    public UserDto create(UserDto userDto) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException {
        log.debug("Creating user");
        userDto.setRole(User.Role.CUSTOMER);
        validate(userDto);
        User user = userRepository.save(dataMapper.toUser(userDto));
        return dataMapper.toUserDto(user);
    }

    @Override
    public UserDto findById(Long id) throws UserNotFoundException {
        log.debug("Retrieving user by id");
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User with id " + id + " wasn't found."));
        return dataMapper.toUserDto(user);
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        log.debug("Retrieving user by email");
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException("User with email " + email + " wasn't found."));
        return dataMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        log.debug("Retrieving all users with matching last name");
        return userRepository.findByLastName(lastName)
                .stream()
                .map(dataMapper::toUserDto)
                .toList();
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.debug("Retrieving all users");
        return userRepository.findAll()
                .stream()
                .map(dataMapper::toUserDto)
                .toList();
    }

    @Override
    public long countAll() {
        log.debug("Counting all users");
        return userRepository.findAll().size();
    }

    @Override
    public UserDto update(UserDto userDto) throws
            UserEmailNotUniqueException, UserPasswordNotProvidedException, UserEmailWasNotProvidedException {
        log.debug("Updating user");
        validate(userDto);
        User user = userRepository.save(dataMapper.toUser(userDto));
        return dataMapper.toUserDto(user);
    }

    @Override
    public UserDto login(String email, String password) throws WrongLoginInfoException {
        log.debug("Logging into account");
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword() != null && user.getPassword().equals(password)) {
                return dataMapper.toUserDto(user);
            } else {
                throw new WrongLoginInfoException("Wrong password given for user with such email " + email + ".");
            }
        } else {
            throw new WrongLoginInfoException("User with such email doesn't exist(email: " + email + ")");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting user by id");
        userRepository.deleteById(id);
    }
}
