package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
       String res=null;
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
	      //  int rescode=authorize();
	    //    rescode_str=String.valueOf(rescode);//Now it will return "10"  
	HelloWorldController hwc=new HelloWorldController();
	res=hwc.authorize(obj);
           
            
	    
          
}catch(Exception e){
	text = "last ctach"+e.getMessage();
}

	       return  "{speech: "+res+",displayText: "+res+", source: biz-webhook-sample}";
	       //return  "{speech: "+text+" source "+source+",displayText: "+text+", source: biz-webhook-sample}";
	    }
	
	public String authorize(String obj){
			 StringBuilder result=null;
                 int statusCode = 0;
		 try {
            int BUFFER_SIZE = 4096;
            URL url = new URL("http://34.196.246.23:8101/MessagingToolController?webhookrespo=hi");
             HttpURLConnection http = (HttpURLConnection) url.openConnection();

             http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "text/html");
            http.setUseCaches(false);
        
            http.setDoOutput(true);
            JSONObject json = new JSONObject();
            json.put("obj",  obj);
//             obj.put("projectId", 1);
//             obj.put("workflowName", skillFlowContext.getWorkflow());
            
            DataOutputStream wr = new DataOutputStream(http.getOutputStream());
 
            wr.writeBytes(json.toString());
 
            wr.flush();
 
            wr.close();
   
             statusCode = http.getResponseCode();
            System.out.println("code=======" + statusCode);
            String newLine = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            result = new StringBuilder();
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                result.append(flag ? newLine : "").append(line);
                flag = true;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error  " + e.getMessage());
        }
               //  return result;
                 return result.toString();
  
       	}
	  
	
}
