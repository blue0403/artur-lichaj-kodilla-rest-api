package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://blue0403.github.io/");
        context.setVariable("preview_message", "YOU HAVE NEW CARD ON TRELLO");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_config", companyConfig);
        context.setVariable("goodbye", "Thank you for choosing us!");
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildSchedulerEmail(String message) {

        ArrayList<String> functionality = new ArrayList<>();
        functionality.add("Sends update everyday at 10 am");
        functionality.add("Provides number of active tasks");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "https://blue0403.github.io/");
        context.setVariable("preview_message", "NUMBER OF TASKS IN DATABASE");
        context.setVariable("button", "Visit Crud App");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_config", companyConfig);
        context.setVariable("scheduler_functionality", functionality);

        return templateEngine.process("mail/scheduled-mail", context);
    }
}
