package org.vaadin.tma.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import org.vaadin.touchkit.ui.DatePicker;
import org.vaadin.touchkit.ui.EmailField;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

@SuppressWarnings("serial")
public class FormView extends NavigationView {

    public FormView() {
        setCaption("Form");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        final TextField nameField = new TextField("Name");
        nameField.setPlaceholder("Enter your name...");
        content.addComponent(nameField);

        final DatePicker dateField = new DatePicker("Date of Birth");
        content.addComponent(dateField);

        final EmailField emailField = new EmailField("Email");
        emailField.setPlaceholder("Enter your email address...");
        content.addComponent(emailField);

        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Thanks!");
            }
        });

        setContent(new CssLayout(content, submitButton));
    }

}
