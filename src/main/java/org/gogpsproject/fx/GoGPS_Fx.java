package org.gogpsproject.fx;
	
import java.util.logging.Logger;

import org.gogpsproject.fx.model.FirebugConsole;
import org.gogpsproject.fx.model.GoGPSDef;
import org.gogpsproject.fx.model.GoGPSModel;
import org.gogpsproject.fx.model.SystemDef;

import net.java.html.BrwsrCtx;
import net.java.html.boot.BrowserBuilder;
import net.java.html.json.Models;
import netscape.javascript.JSException;

public class GoGPS_Fx {
  private static final Logger logger = Logger.getLogger(GoGPS_Fx.class.getName());
  static GoGPSModel goGPSModel;
  
  public static void main(String... args) throws Exception {
    System.out.println("Library Path is " + System.getProperty("java.library.path"));
    System.out.println("rootdir is " + System.getProperty("user.dir"));

    String rootdir = System.getProperty("user.dir") + "/src/main/webapp";
    System.setProperty("browser.rootdir", rootdir ); 
    //System.out.println("browser.rootdir is " + System.getProperty("browser.rootdir"));
    
    BrowserBuilder bb = BrowserBuilder.newBrowser();
    
    BrowserBuilder.newBrowser().
    loadPage("pages/index.html").
    loadClass(GoGPS_Fx.class).
    invoke("onPageLoad").
    showAndWait();
    System.exit(0);
  }
  
  public static void onPageLoad() throws Exception {
//      if( goGPSModel != null ){
//        GoGPSDef.cleanUp(goGPSModel);
//      }
      BrwsrCtx ctx = BrwsrCtx.findDefault(GoGPS_Fx.class);
      FirebugConsole.init(ctx);

      goGPSModel = new GoGPSModel();
      GoGPSDef.init(goGPSModel);
      
      Models.toRaw(goGPSModel);
      GoGPSDef.registerModel();
      goGPSModel.applyBindings();

      // test Serialio. If it fails, copy native library to root
      for( int i=0; i<2; i++ ){
        try {
          GoGPSDef.getPorts( goGPSModel );
          System.out.println("RXTX Ok");
          break;
        }
        catch( JSException jse ){
          System.err.println(jse);
          break;
        }
        catch (Throwable localThrowable){
          System.err.println(localThrowable + " thrown while loading " + "gnu.io.RXTXCommDriver");
          System.err.flush();
          if( i == 0 ){
            SystemDef.copyRxTxLibToRoot( goGPSModel.getSystem() );
            GoGPSDef.alert("RXTX lib copied to root, you might have to restart the app");
          }
        }
      }
    }    
}

