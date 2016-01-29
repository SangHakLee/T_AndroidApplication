import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class GCMSender {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GCMSender();
		aa();

	}
	
	static void aa(){
      String key = "AIzaSyCogJNaplPwBVImUJv87XOxtWvGNQw5ta8";
      String regId = "fNhtUgdEDyE:APA91bEA9EwAXbIBEK-BkNTX58t5-2aYjpFMTgQQVHvjGShBMy0s8DvzPz83Gm4ybCdva-13YBMwO_tnbfDNaS0PhGeukGOE1AVfuvYhFe4GW9TjE4KeIbDQk13Z5ElpWxHYs17qfJy_";
      System.out.println("key : " + key);
      Sender sender = null;

      //다중 REGID 값
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("tempregid1");
//		list.add("tempregid2");
//		list.add("tempregid3");
//
//		MulticastResult multi = null;
//		List<Result> results = null;

      try{
          sender = new Sender(key);
      }catch(Exception e){
    	  System.out.println("key : " + key);
         
      }

//		Message message = new Message.Builder().addData("code", "1").addData("result", "GCMTest 축하드립리다.").build();
//		Message message = new Message.Builder().addData("code", "2").addData("result", "http://m.naver.com").build();
//		Message message = new Message.Builder().addData("code", "3").addData("result", "37.554644,126.970700").build();
//      Message message = new Message.Builder().addData("code", "1").addData("message", "화면 켜고 Main 실행").build();
      Message message = new Message.Builder().addData("code", "2").addData("message", "http://m.naver.com").build();
      


      try{
          //멀티 regId 전송
//			multi = sender.send(message, list, 5);
//			results = multi.getResults();

          Result result = sender.send(message, regId, 5); // 3번째 인자 재실행 회수
          if (result.getMessageId() != null) { // 정상 보내짐

              System.out.println("send success");
              String canonicalRegId = result
                      .getCanonicalRegistrationId();
              if (canonicalRegId != null) {
            	  System.out.println("key : " + key);
              }
          } else { // 실패
              String error = result.getErrorCodeName();
              if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
            	  System.out.println("key : " + key);

              } else {
            	  System.out.println("key : " + key);
              }
          }
      }catch(Exception e){
    	  System.out.println("key : " + key);
      }
  }

}
