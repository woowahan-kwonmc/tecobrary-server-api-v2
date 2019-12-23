package com.woowacourse.tecobrary.common.application;

import com.woowacourse.tecobrary.common.application.api.SlackBotWebClient;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlackBotService {

    private static final Logger log = LoggerFactory.getLogger(SlackBotService.class);

    private SlackBotWebClient slackBotWebClient;

    @Autowired
    public SlackBotService(final SlackBotWebClient slackBotWebClient) {
        this.slackBotWebClient = slackBotWebClient;
    }

    public void wishBookNotification(final LibraryBookDto libraryBookDto) {
        String response = slackBotWebClient.wishBookNotification(libraryBookDto);
        log.info("SlackBot Wish Book Notification Response : {}", response);
    }

    public void enrolledNotification(final LibraryBookDto libraryBookDto) {
        String response = slackBotWebClient.enrolledNotification(libraryBookDto);
        log.info("SlackBot Enrolled Notification Response : {}", response);
    }
}
