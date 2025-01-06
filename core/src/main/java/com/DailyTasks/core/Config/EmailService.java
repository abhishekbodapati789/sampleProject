package com.DailyTasks.core.Config;

public interface EmailService {

    void sendEmail(String toEmail, String ccEmail, String fromEmail, String subject, String content);
}
