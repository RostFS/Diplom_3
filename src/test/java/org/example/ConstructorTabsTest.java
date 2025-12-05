package org.example;

import org.example.pages.MainPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTabsTest extends BaseTest {

    @Test
    public void constructorBunsTabIsActiveByDefault() {
        MainPage mainPage = new MainPage(driver);

        String activeText = mainPage.getActiveTabText();

        assertEquals("Булки", activeText);
    }

    @Test
    public void canSwitchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();
        String activeText = mainPage.getActiveTabText();

        assertEquals("Соусы", activeText);
    }

    @Test
    public void canSwitchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();
        String activeText = mainPage.getActiveTabText();

        assertEquals("Начинки", activeText);
    }
}
