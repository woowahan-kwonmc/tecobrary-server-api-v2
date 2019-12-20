/*
 * @(#) UserJwtInfoVo.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui.vo;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserJwtInfoVo {

    private String userNo;
    private String email;
    private String name;
    private String avatarUrl;
    private String authorization;

    @Builder
    private UserJwtInfoVo(final Long userNo, final String email, final String name, final String avatarUrl, final String authorization) {
        this.userNo = String.valueOf(userNo);
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
