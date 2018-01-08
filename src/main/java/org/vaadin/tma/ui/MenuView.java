package org.vaadin.tma.ui;

import org.vaadin.touchkit.ui.NavigationButton;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;
import org.vaadin.viritin.label.MLabel;

@SuppressWarnings("serial")
public class MenuView extends NavigationView {

    public MenuView() {
        setCaption("What?");

        final VerticalComponentGroup content = new VerticalComponentGroup();

        content.addComponent(new MLabel("This a mobile friendly version of mapant.fi, but also a getting started example app of TouchKit for Vaadin 8.X. If you are here for the map, just ignore this page.").withFullWidth());

        content.addComponent(new MLabel("BTW. Adding the app to your phones home screen will open it as an 'app' and you'll get bigger Map.").withFullWidth());
        NavigationButton button = new NavigationButton("Form");
        button.addClickListener(e -> {
            getNavigationManager().navigateTo(new FormView());
        });
        content.addComponent(button);
        setContent(content);
    }
;
}
