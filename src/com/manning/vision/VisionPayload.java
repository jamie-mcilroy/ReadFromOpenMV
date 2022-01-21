package com.manning.vision;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "payloadType")
@JsonSubTypes(
		{
			@JsonSubTypes.Type(value = VisionPayloadStrip.class, name = "Strip")
		}
		)
public class VisionPayload {

	private String payloadType;
	private Double yPos;

	
	public void setPayloadType(String payloadType) {
		this.payloadType = payloadType;
	}

	public String getPayloadType() {
		return payloadType;
	}

	public Double getyPos() {
		return yPos;
	}
	public void setyPos(Double yPos) {
		this.yPos = yPos;
	}
	@Override
	public String toString() {
		return String.format("%s with a yPos of %s", getPayloadType(), getyPos());
	}

}
