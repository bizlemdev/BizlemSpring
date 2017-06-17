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
       String source = null;
       String name=null;
       String respostring=null;
       String text1=null;
       String rescode_str=null;
try{
	    	JSONObject objS = new JSONObject(obj);
//          System.out.println(objS.get("id"));
          JSONObject result = objS.getJSONObject("result");
	        text = result.getString("resolvedQuery");
	  JSONObject originalRequest = objS.getJSONObject("originalRequest");
	        source = originalRequest.getString("source");
	JSONObject data = originalRequest.getJSONObject("data");
	JSONObject user = data.getJSONObject("user");
	        name=user.getString("name");

	        text1="I love India";
	        int rescode=authorize();
	        rescode_str=String.valueOf(rescode);//Now it will return "10"  
           
            
	    
          
}catch(Exception e){
	text = "last ctach"+e.getMessage();
}

	       return  "{speech: "+rescode_str+",displayText: "+rescode_str+", source: biz-webhook-sample}";
	       //return  "{speech: "+text+" source "+source+",displayText: "+text+", source: biz-webhook-sample}";
	    }
	
	public static int authorize(){
 
		 StringBuilder result=null;
                 int statusCode = 0;
		 try {
            int BUFFER_SIZE = 4096;
            bundle = ResourceBundle.getBundle("config");
          //  URL url = new URL(bundle.getString("rbac_ip"));
              URL url = new URL("http://34.196.246.23:9500?source=skype&sourceId=Akhilesh Yadav");
 
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
 
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/json");
         //   http.setRequestProperty("Authorization", "Bearer "+skillFlowContext.getAccessToken());
         //    http.setUseCaches(false);
 
       //     http.setDoOutput(true);
 
//             JSONObject obj = new JSONObject();
//             obj.put("userName",  skillFlowContext.getSessionId());
//             obj.put("projectId", 1);
//             obj.put("workflowName", skillFlowContext.getWorkflow());
            
//             DataOutputStream wr = new DataOutputStream(http.getOutputStream());
 
//             wr.writeBytes(obj.toString());
 
//             wr.flush();
 
//             wr.close();
            
         
             statusCode = http.getResponseCode();
            System.out.println("code=======" + statusCode);
         
        } catch (Exception e) {
            System.out.println("error  " + e.getMessage());
        }
               //  return result;
                 return statusCode;
	}
}
