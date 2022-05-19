package com.nhtr.accountservice.controller;

import com.nhtr.accountservice.client.ProfileApi;
import com.nhtr.accountservice.client.model.LogoutRequest;
import com.nhtr.accountservice.client.model.LogoutResponse;
import com.nhtr.accountservice.client.model.UserProfileResponse;
import com.nhtr.accountservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {

    private final ProfileService profileService;

    @Override
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        return ResponseEntity.ok().body(profileService.getProfile());
    }

    @Override
    public ResponseEntity<LogoutResponse> postUserLogout(LogoutRequest logoutRequest) {
        return ResponseEntity.ok().body(profileService.logout(logoutRequest));
    }

}
