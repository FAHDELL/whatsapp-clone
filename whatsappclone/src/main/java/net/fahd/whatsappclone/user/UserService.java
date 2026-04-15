package net.fahd.whatsappclone.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper ;

    public List<UserResponse> getAllUsersExceptSelf(Authentication connectedUser) {

        System.out.println("connectedUser " + connectedUser.getName());

        return userRepository.findAllUsersExceptSelf(connectedUser.getName())
                .stream()
                .map(userMapper::toUserResponse)
                .toList();


    }
}
