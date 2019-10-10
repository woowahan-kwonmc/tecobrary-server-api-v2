/*
 * @(#) GithubUserApiUtils.java
 *
 * v 1.0.0
 *
 * 2019.10.03
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.common.util.BodyParser;
import com.woowacourse.tecobrary.user.ui.vo.GithubEmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GithubUserApi {

    private static final String ACCESS_TOKEN = "access_token";

    private final GithubUserApiUrlBuilder githubUserApiUriBuilder;
    private final GithubApiClient githubApiClient;

    @Autowired
    public GithubUserApi(GithubUserApiUrlBuilder githubUserApiUriBuilder,
                         GithubApiClient githubApiClient) {
        this.githubUserApiUriBuilder = githubUserApiUriBuilder;
        this.githubApiClient = githubApiClient;
    }

    public String getPrimaryEmail(String githubApiUserEmailClientResponse) {
        List<GithubEmailVo> githubEmailVo = GsonUtils.parseUserEmail(githubApiUserEmailClientResponse);
        return githubEmailVo.stream()
                .filter(GithubEmailVo::isPrimary)
                .map(GithubEmailVo::getEmail)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근"));
    }

    public String githubUserApiAccessToken(String code) {
        String githubApiUrl = githubUserApiUriBuilder.api(code);
        String accessToken = githubApiClient.accessToken(githubApiUrl);
        Map<String, String> response = BodyParser.parse(accessToken);
        return response.get(ACCESS_TOKEN);
    }
}
