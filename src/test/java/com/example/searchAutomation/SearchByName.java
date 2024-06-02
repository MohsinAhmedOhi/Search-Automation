package com.example.searchAutomation;

import com.microsoft.playwright.*;

public class SearchByName {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
        String searchInput = "All Eyes On Rafah";

        Page page = browser.newContext().newPage();
        page.navigate("https://www.google.com/");

        // Locate the search bar element and type the search term
        Locator searchBar = page.locator("//textarea[@id='APjFqb']");
        searchBar.fill(searchInput);

        // Submit the search (consider using a submit button locator if needed)
        searchBar.press("Enter");

        // Wait for search results to load (better approach)
        page.waitForLoadState();

        // Extract and display search results
        java.util.List<ElementHandle> searchResultLinks = page.querySelectorAll("h3");
        System.out.println("Search results for \"" + searchInput + "\":");
        for (ElementHandle link : searchResultLinks) {
            String resultText = link.innerText();
            System.out.println(resultText);
        }

        browser.close();
    }
}
