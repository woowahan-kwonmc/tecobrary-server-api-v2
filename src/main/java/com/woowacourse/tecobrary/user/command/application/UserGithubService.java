package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.util.UserGithubInfoMapper;
import com.woowacourse.tecobrary.user.infra.util.UserJwtVoMapper;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubService {

    private final UserService userService;
    private final GithubApiService githubApiService;

    @Autowired
    public UserGithubService(UserService userService, GithubApiService githubApiService) {
        this.userService = userService;
        this.githubApiService = githubApiService;
    }

    public UserJwtInfoVo getUserByGithubInfo(String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubApiService.getGithubUserInfo(githubApiAccessToken);
        try {
            return UserJwtVoMapper.map(userService.findByGithubId(githubUserInfoVo.getId()));
        } catch (NotFoundGithubUserException e) {
            return UserJwtVoMapper.map(getNewUserAfterSave(githubApiAccessToken, githubUserInfoVo));
        }
    }

    private User getNewUserAfterSave(String githubApiAccessToken, GithubUserInfoVo githubUserInfoVo) {
        UserGithubInfo userGithubInfo = UserGithubInfoMapper.map(githubUserInfoVo,
                githubApiService.getGithubUserEmail(githubApiAccessToken));
        return userService.save(userGithubInfo);
    }
}
