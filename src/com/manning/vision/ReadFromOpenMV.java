
package com.manning.vision;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ReadFromOpenMV {
	
	public static void main(String[] args) {
		ObjectMapper om = new ObjectMapper();
		SerialPort comPort = SerialPort.getCommPort("/dev/tty.usbmodem336B386431301");
		
		comPort.openPort();
		comPort.addDataListener(new SerialPortDataListener() {
		   @Override
		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
		   @Override
		   public void serialEvent(SerialPortEvent event)
		   {
			  long start = System.currentTimeMillis();
		      byte[] newData = event.getReceivedData();
		      try {
				VisionPayload readValue = om.readValue(newData, VisionPayload.class);
				long totalTime = System.currentTimeMillis() -start;
				System.out.println(String.format("%s in %s",readValue,totalTime));
			} catch (IOException e) {
				e.printStackTrace();
			}
		      
		   }
		});
		try {
 			System.out.println("Press Enter to exit");
			System.in.read();
			System.out.println("Exiting");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			comPort.closePort();
		}
		
	}

}
