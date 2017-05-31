package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String webhook(@RequestBody String obj){
       String text = null;
       String respostring=null;
       
try{
	    	JSONObject objS = new JSONObject(obj);
//          System.out.println(objS.get("id"));
          JSONObject result = objS.getJSONObject("result");
           text = result.getString("resolvedQuery");
            
	    
          
}catch(Exception e){
	text = "last ctach"+e.getMessage();
}

	       return  "{speech: "+text+",displayText: "+text+", source: biz-webhook-sample}";
	    }
}
