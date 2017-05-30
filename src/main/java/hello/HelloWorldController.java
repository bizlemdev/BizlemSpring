package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){
        String text="";
try{
        
        JSONObject objS = new JSONObject(obj);
//          System.out.println(objS.get("id"));
          JSONObject result = objS.getJSONObject("result");
          text = result.getString("resolvedQuery");
        System.out.println(text);
}catch(Exception e){}
        return new WebhookResponse(text, text);
    }
}
