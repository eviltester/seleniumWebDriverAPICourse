package com.seleniumsimplified.webdriver.siteabstractions;

import java.util.HashMap;
import java.util.Map;

public class SiteUrls {

    /*
    An abstraction around the site for testing to make it easier to
    switch between environments, vms, containers or local
     */

    // TODO: configure this via env variables or config
    static String domain(){ return "testpages.herokuapp.com"; }
    public static String rootUrl(){ return "https://" + domain(); }

    // the actual pages
    static final Map<PAGE_NAME, String> pages = getMapOfPages();

    private static Map<PAGE_NAME, String> getMapOfPages() {
        Map<PAGE_NAME, String> pages = new HashMap<>();
        pages.put(PAGE_NAME.COMPLEX_IFRAMES_TEST_PAGE, "frames/index.html");
        pages.put(PAGE_NAME.IFRAMES_TEST_PAGE, "styled/iframes-test.html");
        pages.put(PAGE_NAME.FRAMES_TEST_PAGE, "styled/frames/frames-test.html");
        pages.put(PAGE_NAME.BASIC_HTML_FORM_PAGE, "basic_html_form.html");
        pages.put(PAGE_NAME.FIND_BY_PLAYGROUND_PAGE, "find_by_playground.php");
        pages.put(PAGE_NAME.BASIC_WEB_PAGE, "basic_web_page.html");
        pages.put(PAGE_NAME.BASIC_AJAX_PAGE, "basic_ajax.html");
        pages.put(PAGE_NAME.ALERTS_PAGE, "styled/alerts/alert-test.html");
        pages.put(PAGE_NAME.JAVASCRIPT_COUNTDOWN, "javascript_countdown.html");
        pages.put(PAGE_NAME.FAKE_ALERTS_TEST_PAGE, "styled/alerts/fake-alert-test.html");
        pages.put(PAGE_NAME.WINDOW_SIZE_MEDIA_QUERIES_TEST_PAGE, "styled/css-media-queries.html");
        pages.put(PAGE_NAME.WINDOW_LINKS_TEST_PAGE, "styled/windows-test.html");
        pages.put(PAGE_NAME.SEARCH_ENGINE_PAGE, "search.php");
        pages.put(PAGE_NAME.BASIC_REDIRECT_PAGE, "basic_redirect.html");
        pages.put(PAGE_NAME.BASIC_CALCULATOR_PAGE, "calculate.php");
        pages.put(PAGE_NAME.BASIC_GUI_INTERACTIONS, "gui_user_interactions.html");
        pages.put(PAGE_NAME.USER_AGENT_REDIRECT, "styled/redirect/user-agent-redirect-test");
        pages.put(PAGE_NAME.CANVAS_BASIC_TEST_PAGE, "canvas_basic.html");

        return pages;
    }

    static String pageOffRoot(PAGE_NAME page){
        return rootUrl() + "/" + pages.getOrDefault(page, "")
    ;}

    public static String framesTestPageUrl(){ return pageOffRoot(PAGE_NAME.FRAMES_TEST_PAGE); }
    public static String iframesTestPageUrl(){ return pageOffRoot(PAGE_NAME.IFRAMES_TEST_PAGE); }
    public static String basicHtmlFormPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_HTML_FORM_PAGE); }
    public static String findByPlaygroundPageUrl(){ return pageOffRoot(PAGE_NAME.FIND_BY_PLAYGROUND_PAGE); }
    public static String basicWebPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_WEB_PAGE); }
    public static String basicAjaxPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_AJAX_PAGE); }
    public static String basicAlertsPageUrl(){ return pageOffRoot(PAGE_NAME.ALERTS_PAGE); }
    public static String fakeAlertsPageUrl(){ return pageOffRoot(PAGE_NAME.FAKE_ALERTS_TEST_PAGE); }
    public static String javascriptCountdownPageUrl(){ return pageOffRoot(PAGE_NAME.JAVASCRIPT_COUNTDOWN); }
    public static String windowSizeMediaQueriesPageUrl(){ return pageOffRoot(PAGE_NAME.WINDOW_SIZE_MEDIA_QUERIES_TEST_PAGE); }
    public static String windowLinksPageUrl(){ return pageOffRoot(PAGE_NAME.WINDOW_LINKS_TEST_PAGE); }
    public static String searchPageUrl(){ return pageOffRoot(PAGE_NAME.SEARCH_ENGINE_PAGE); }
    public static String redirectPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_REDIRECT_PAGE); }
    public static String basicCalculatorPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_CALCULATOR_PAGE); }
    public static String basicGuiInteractionsPageUrl(){ return pageOffRoot(PAGE_NAME.BASIC_GUI_INTERACTIONS);}
    public static String useragentRedirectPageUrl(){ return pageOffRoot(PAGE_NAME.USER_AGENT_REDIRECT);}
    public static String basicCanvasPageUrl(){ return pageOffRoot(PAGE_NAME.CANVAS_BASIC_TEST_PAGE);}




}
