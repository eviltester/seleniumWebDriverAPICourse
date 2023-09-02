package com.seleniumsimplified.webdriver.siteabstractions;

import java.util.HashMap;
import java.util.Map;

public class SiteUrls {

    /*
    An abstraction around the site for testing to make it easier to
    switch between environments, vms, containers or local
     */

    // TODO: configure this via env variables or config
//    public static String domain(){ return "localhost:5000"; }
//    public static String protocol(){ return "http"; }
//    public static String rootUrl(){ return protocol() + "://" + domain(); }

    public static String domain(){ return "testpages.eviltester.com"; }
    public static String protocol(){ return "https"; }
    public static String rootUrl(){ return protocol() + "://" + domain(); }

    // the actual pages
    static final Map<PAGE_NAME, String> pages = getMapOfPages();

    private static Map<PAGE_NAME, String> getMapOfPages() {
        Map<PAGE_NAME, String> pages = new HashMap<>();
        pages.put(PAGE_NAME.INDEX_PAGE, "styled/index.html");
        pages.put(PAGE_NAME.IFRAMES_TEST_PAGE, "styled/iframes-test.html");
        pages.put(PAGE_NAME.FRAMES_TEST_PAGE, "styled/frames/frames-test.html");
        pages.put(PAGE_NAME.FAKE_ALERTS_TEST_PAGE, "styled/alerts/fake-alert-test.html");
        pages.put(PAGE_NAME.WINDOW_SIZE_MEDIA_QUERIES_TEST_PAGE, "styled/css-media-queries.html");
        pages.put(PAGE_NAME.WINDOW_LINKS_TEST_PAGE, "styled/windows-test.html");
        pages.put(PAGE_NAME.USER_AGENT_REDIRECT, "styled/redirect/user-agent-redirect-test");

        // Migrated from legacy pages to the styled pages
        pages.put(PAGE_NAME.BASIC_HTML_FORM_PAGE, "styled/basic-html-form-test.html");
        pages.put(PAGE_NAME.FIND_BY_PLAYGROUND_PAGE, "styled/find-by-playground-test.html");
        pages.put(PAGE_NAME.BASIC_WEB_PAGE, "styled/basic-web-page-test.html");
        pages.put(PAGE_NAME.BASIC_AJAX_PAGE, "styled/basic-ajax-test.html");
        pages.put(PAGE_NAME.ALERTS_PAGE, "styled/alerts/alert-test.html");
        pages.put(PAGE_NAME.JAVASCRIPT_COUNTDOWN, "styled/javascript-countdown-test.html");
        pages.put(PAGE_NAME.SEARCH_ENGINE_PAGE, "styled/search");
        pages.put(PAGE_NAME.BASIC_REDIRECT_PAGE, "styled/javascript-redirect-test.html");
        pages.put(PAGE_NAME.BASIC_CALCULATOR_PAGE, "styled/calculator");
        pages.put(PAGE_NAME.DRAG_N_DROP, "styled/drag-drop-javascript.html");
        pages.put(PAGE_NAME.CANVAS_SCRIBBLE, "styled/gui-scribble.html");
        pages.put(PAGE_NAME.CANVAS_BASIC_TEST_PAGE, "styled/canvas-javascript-test.html");
        pages.put(PAGE_NAME.REFRESH_TEST_PAGE, "styled/refresh");

        //pages.put(PAGE_NAME.BASIC_HTML_FORM_PAGE, "basic_html_form.html");
        //pages.put(PAGE_NAME.FIND_BY_PLAYGROUND_PAGE, "find_by_playground.php");
        //pages.put(PAGE_NAME.BASIC_WEB_PAGE, "basic_web_page.html");
        //pages.put(PAGE_NAME.BASIC_AJAX_PAGE, "basic_ajax.html");
        // pages.put(PAGE_NAME.ALERTS_PAGE, "alerts.html");
        //pages.put(PAGE_NAME.JAVASCRIPT_COUNTDOWN, "javascript_countdown.html");
        //pages.put(PAGE_NAME.SEARCH_ENGINE_PAGE, "search.php");
        //pages.put(PAGE_NAME.BASIC_REDIRECT_PAGE, "basic_redirect.html");
        //pages.put(PAGE_NAME.BASIC_CALCULATOR_PAGE, "calculate.php");
        //pages.put(PAGE_NAME.DRAG_N_DROP, "gui_user_interactions.html");
        //pages.put(PAGE_NAME.CANVAS_SCRIBBLE, "gui_user_interactions.html");
        //pages.put(PAGE_NAME.CANVAS_BASIC_TEST_PAGE, "canvas_basic.html");
        //pages.put(PAGE_NAME.REFRESH_TEST_PAGE, "refresh.php");

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
    public static String canvasScribblePageUrl(){ return pageOffRoot(PAGE_NAME.CANVAS_SCRIBBLE);}
    public static String dragAndDropPageUrl(){ return pageOffRoot(PAGE_NAME.DRAG_N_DROP);}
    public static String useragentRedirectPageUrl(){ return pageOffRoot(PAGE_NAME.USER_AGENT_REDIRECT);}
    public static String basicCanvasPageUrl(){ return pageOffRoot(PAGE_NAME.CANVAS_BASIC_TEST_PAGE);}
    public static String refreshPage(){ return pageOffRoot(PAGE_NAME.REFRESH_TEST_PAGE);}
}
