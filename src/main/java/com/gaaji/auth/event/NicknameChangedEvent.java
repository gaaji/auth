package com.gaaji.auth.event;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class NicknameChangedEvent extends Event{

    private final String userId;
    private final String nickname;

}
