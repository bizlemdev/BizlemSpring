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
	      //  int rescode=authorize();
	    //    rescode_str=String.valueOf(rescode);//Now it will return "10"  
	HelloWorldController hwc=new HelloWorldController();
	String res=hwc.authorize(obj);
           
            
	    
          
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
            http.setRequestProperty("Content-Type", "application/json");
      
             http.setUseCaches(false);
        
            http.setDoOutput(true);
   
            JSONObject json = new JSONObject();
            json.put("userName",  obj.toString());
          
           DataOutputStream wr = new DataOutputStream(http.getOutputStream());
           wr.writeBytes(json.toString());
           wr.flush();
           wr.close();
            
            /******************/
             /*System.out.println(" nlp_response step 8 : ");
             InputStream inputStream1= new ByteArrayInputStream(obj.toString().getBytes());
 System.out.println(" nlp_response step 9 : ");
            OutputStream outputStream = http.getOutputStream();
 System.out.println(" nlp_response step 10 : ");
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream1.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
             System.out.println(" nlp_response step 11 : ");
            outputStream.flush();
            outputStream.close();
            inputStream1.close();*/
            /******************/
         
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
                 return result;
	}
	
}
