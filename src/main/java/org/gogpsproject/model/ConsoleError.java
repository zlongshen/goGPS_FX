package org.gogpsproject.model;

public class ConsoleError extends FirebugConsole{
  @net.java.html.js.JavaScriptBody(args = { "msg" }, body = ""
      + "Firebug.Console.error(msg);")
  public native void log( String msg );
}