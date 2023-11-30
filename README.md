# AltSvcWebView

This is meant to demonstrate the behavior with [Alt-Svc header](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Alt-Svc).

This setup has Application that loads up the WebView and URL supporting Http3.


# Steps to repro
1. Monitor the logs using `adb logcat | grep -i WEB_VIEW_TEST`
2. Run the application on a device. Let it load the website. Observe the value of the protocol. It will be Http2. Demo queries javascript using `performance.getEntriesByType('navigation')[0].nextHopProtocol` API to log this value.

`
I WEB_VIEW_TEST: Protocol used was "h2"
`

3. Now click on reload and observe the value of the protocol. It will be http3.

`
I WEB_VIEW_TEST: Protocol used was "h3"
`


3. Restart the app and observe the value of the protocol. It will be http2 and not http3.

# How does other platform behave
## Mac Canary - 121.0.6156.2
## Android Chrome - 119.0.6045.134
1. Open Mac Canary
2. Visit any website that supports http3. i.e. https://m.youtube.com/
3. Check the value of the protocol in Chrome DevTools or using `performance.getEntriesByType('navigation')[0].nextHopProtocol`. Ensure it is http3. Reload till it connects over http3.
4. Close the browser completely. Reopen.
5. Visit same site and check the protocol.
6. It will be Http3 and not http2.

