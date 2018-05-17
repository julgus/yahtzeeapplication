package com.julia;

import javax.servlet.annotation.WebServlet;
import javax.xml.soap.Text;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private final String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();

    private final FileResource dice = new FileResource(new File(basepath +
            "/WEB-INF/images/dice.png"));

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addStyleName("app-background");
        mainLayout.setSizeFull();

        Image diceImg = new Image(null, dice);
        diceImg.setWidth(50, Unit.PERCENTAGE);
        diceImg.setStyleName("dice-img");

        Label byAuthorLabel = new Label("CLASSIC");
        byAuthorLabel.setSizeFull();
        byAuthorLabel.setStyleName("regular-label");

        Label yahtzeeTitle = new Label("YAHTZEE GAME");
        yahtzeeTitle.setSizeFull();
        yahtzeeTitle.setHeightUndefined();
        yahtzeeTitle.addStyleName("yahtzee-title");

        Button startBtn = new Button("START NEW GAME");
        startBtn.addStyleName("start-button");
        startBtn.setSizeFull();
        startBtn.setStyleName(ValoTheme.BUTTON_BORDERLESS);

        VerticalLayout border = new VerticalLayout(byAuthorLabel, yahtzeeTitle, startBtn);
        border.addStyleName("title-border");
        border.setWidth(100, Unit.PERCENTAGE);
        border.setHeightUndefined();

        mainLayout.addComponents(diceImg, border);
        mainLayout.setExpandRatio(border, 1);
        mainLayout.setComponentAlignment(border, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(diceImg, Alignment.BOTTOM_CENTER);
        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
