Call Notifier
=============

Call Notifier is a free app that will execute a HTTP GET or POST request when receiving a phone call or a text message. You can program your XBMC, your home automation system or any other system to show a message or launch an action.

When you receive a phone call, 2 parameters are sent: name and number. The app will look into your contacts to find the name corresponding to the number. If there is no match, name will be an empty string.
When it's a text message, a third parameter is sent containing the message. So you have just to verify is the parameter message exists to find out if it's a phone call or a message.

A PHP example can be found in this repository.

To download the Android app: https://play.google.com/store/apps/details?id=fr.nawrasg.callnotifier
