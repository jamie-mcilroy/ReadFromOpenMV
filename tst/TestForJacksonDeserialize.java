

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.vision.VisionPayload;

public class TestForJacksonDeserialize {

	
	@Test
	public void test4627VisionJson()  {
		String visionString = "{\"payloadType\":\"Strip\", \"yPos\":12.5}";
		
		ObjectMapper om = new ObjectMapper();
		VisionPayload vp;
		try {
			vp = om.readValue(visionString, VisionPayload.class);
			assertEquals("Strip", vp.getPayloadType());
			assertEquals(new Double(12.5), vp.getyPos());
			
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	

}
