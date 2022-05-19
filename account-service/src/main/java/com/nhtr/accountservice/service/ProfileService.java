package com.nhtr.accountservice.service;

import com.nhtr.accountservice.client.model.LogoutRequest;
import com.nhtr.accountservice.client.model.LogoutResponse;
import com.nhtr.accountservice.client.model.UserProfileResponse;

public interface ProfileService {

    UserProfileResponse getProfile();

    LogoutResponse logout(LogoutRequest request);
}
